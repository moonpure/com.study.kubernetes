package com.study.kubernete.user.sensitive;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 为了性，接口做了缓存，但缓存如值为空，则不会脱敏，基于脱敏数据为重要数据，基本不能为null.
 */
@Slf4j
public class SensitiveTool {
    private static final ConcurrentHashMap<String, Boolean> sensitiveCache = new ConcurrentHashMap();
    private static final int DEEP = 20;

    public static String sensitive(SensitiveTypeEnum sensitiveType, String value) {
        return SensitiveReplaceTool.replace(sensitiveType, value);
    }

    public static <T> void sensitive(T t) {
        if (t == null) {
            return;
        }
        if (!isSensitive(t)) {
            return;//不用脱敏
        }
        //下面做脱敏
        sensitive(t, null, 0);
    }

    /**
     * 递归脱敏
     *
     * @param t
     * @param classt
     * @param deep
     * @param <T>
     */

    private static <T> void sensitive(T t, Class<?> classt, Integer deep) {

        if (t == null || deep > DEEP) {
            return;
        }
        //如递归会这些值传过来
        if (classt == null) {
            classt = t.getClass();
        }

        if (Iterable.class.isAssignableFrom(classt)) {
            Iterable values = (Iterable) t;
            for (final Object source : values) {
                Class<?> classGeneric = source.getClass();
                sensitive(source, classGeneric, deep + 1);
                if (ClassTool.isSuper(classGeneric.getSuperclass())) {
                    continue;
                }
//处理父类
                sensitive(source, classGeneric.getSuperclass(), deep + 1);
            }
            return;
        }
        if (classt.isArray()) {
            Class<?> classArray = classt.getComponentType();
            for (int i = 0; i < Array.getLength(t); i++) {
                Object valueArray= Array.get(t, i);
                //Class<?> classArray = valueArray.getClass();

                sensitive(valueArray, classArray, deep + 1);
                if (ClassTool.isSuper(classArray.getSuperclass())) {
                    continue;
                }
                //处理父类
                sensitive(valueArray, classArray.getSuperclass(), deep + 1);
            }
            return;
        }

        Field[] fields = classt.getDeclaredFields();//得到要修改的对象的属性数组
        if (fields == null || fields.length <= 0) {
            return;
        }
        for (Field field : fields) {
            try {
                Class<?> classz = field.getType();
                field.setAccessible(true);  //打破封装
                //基础类型
                if (ClassTool.isPrimitive(classz)) {
                    //只做String
                    if (!ClassTool.isString(classz)) {
                        continue;//不是string，跳
                    }
                    Sensitive sensitive = field.getAnnotation(Sensitive.class);
                    if (sensitive == null || sensitive.type() == SensitiveTypeEnum.NOSENSTIVE) {
                        continue;//此字段不脱敏
                    }
                    Object valueObj = field.get(t); //得到该属性的get方法的值
                    if (valueObj == null) {
                        continue;
                    }
                    //做替换
                    String valueStr = String.valueOf(valueObj);
                    String replace = SensitiveReplaceTool.replace(sensitive.type(), sensitive.regex(), sensitive.index(), sensitive.replacedCharacter(), valueStr);
                    if (StringUtils.hasText(replace)) {
                        field.set(t, replace);
                    }
                    continue;
                }
                if (Iterable.class.isAssignableFrom(classz)) {
                    Class<?> classGeneric = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                    Object valueObj = field.get(t); //得到该属性的get方法的值
                    if (valueObj == null) {
                        continue;
                    }
                    Iterable values = (Iterable) valueObj;
                    for (final Object source : values) {
                        sensitive(source, classGeneric, deep + 1);
                        if (ClassTool.isSuper(classGeneric.getSuperclass())) {
                            continue;
                        }
                        //处理父类
                        sensitive(source, classGeneric.getSuperclass(), deep + 1);
                    }
                    continue;
                }
                if (classz.isArray()) {
                    Class<?> classArray = classz.getComponentType();
                    Object valueObj = field.get(t); //得到该属性的get方法的值
                    if (valueObj == null) {
                        continue;
                    }
                    for (int i = 0; i < Array.getLength(valueObj); i++) {
                        sensitive(Array.get(valueObj, i), classArray, deep + 1);
                        if (ClassTool.isSuper(classArray.getSuperclass())) {
                            continue;
                        }
                        //处理父类
                        sensitive(Array.get(valueObj, i), classArray.getSuperclass(), deep + 1);
                    }
                    continue;
                }

                if (Map.class.isAssignableFrom(classz)) {
                    continue;//map不处理
                }
                //处理泛型
                Type genericType = field.getGenericType();
                if (genericType != null && genericType.getTypeName().equals("T")) {
                    Object value = field.get(t);
                    if (value == null) {
                        continue;
                    }
                    Class<?> valueClass = value.getClass();
                    if (valueClass == null) {
                        continue;
                    }
                    //list
                    if (Iterable.class.isAssignableFrom(valueClass)) {
                        Iterable values = (Iterable) value;
                        for (final Object source : values) {
                            Class<?> classGeneric = source.getClass();
                            sensitive(source, classGeneric, deep + 1);
                            if (ClassTool.isSuper(classGeneric.getSuperclass())) {
                                continue;
                            }
                            //父类
                            sensitive(source, classGeneric.getSuperclass(), deep + 1);
                        }
                        continue;
                    }
                    //[]
                    if (valueClass.isArray()) {
                        for (int i = 0; i < Array.getLength(value); i++) {
                            Object valueArray= Array.get(value, i);
                            Class<?> classArray = valueArray.getClass();
                            sensitive(valueArray, classArray, deep + 1);
                            if (ClassTool.isSuper(classArray.getSuperclass())) {
                                continue;
                            }
                            //处理父类
                            sensitive(valueArray, classArray, deep + 1);
                        }
                        continue;
                    }
                    if (Map.class.isAssignableFrom(valueClass)) {
                        continue;
                    }


                    sensitive(value, value.getClass(), deep + 1);
                    if (ClassTool.isSuper(value.getClass().getSuperclass())) {
                        continue;
                    }
                    //处理父类
                    sensitive(value, value.getClass().getSuperclass(), deep + 1);
                    continue;
                }

            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        if (ClassTool.isSuper(classt.getSuperclass())) {
            //系统类不脱敏
            return;
        }
        //父类脱敏
        sensitive(t, classt.getSuperclass(), deep + 1);
    }


    public static <T> Boolean isSensitive(T t) {
        //生key，考虚泛型
        String keyName = SensitiveGenericKey.buildGenericKeyName(t);

        if (sensitiveCache.containsKey(keyName)) {
            return sensitiveCache.get(keyName);
        }
        boolean isSend = isSensitive(t, null, 0);
        sensitiveCache.put(keyName, isSend);
        return sensitiveCache.get(keyName);
    }


    /**
     * 递归
     *
     * @param t
     * @param classt,递中用，加上，数组，List泛型直接确定一次
     * @param deep
     * @param <T>
     * @return
     */
    private static <T> Boolean isSensitive(T t, Class<?> classt, Integer deep) {
        if (deep > DEEP) {
            return false;
        }

        //递归用加了classt
        if (classt == null && t != null) {
            classt = t.getClass();
        }

        if (Iterable.class.isAssignableFrom(classt)) {
            if (t == null) {
                return false;
            }
            Iterable values = (Iterable) t;
            for (final Object source : values) {
                Class<?> classGeneric = source.getClass();
                if (isSensitive(source, classGeneric, deep + 1)) {
                    return true;
                }
                if (ClassTool.isSuper(classGeneric.getSuperclass())) {
                    return false;
                }
                //处理父类
                return isSensitive(source, classGeneric.getSuperclass(), deep + 1);
            }
        }
        if (classt.isArray()) {
            Class<?> classArray = classt.getComponentType();
            if (isSensitive(null, classArray, deep + 1)) {
                return true;
            }
            if (ClassTool.isSuper(classArray.getSuperclass())) {
                return false;
            }
            //处理父类
            return isSensitive(null, classArray.getSuperclass(), deep + 1);
        }
        Field[] fields = classt.getDeclaredFields();//得到要修改的对象的属性数组
        if (fields == null || fields.length <= 0) {
            return false;
        }
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Class<?> classz = field.getType();
                //基础类型
                if (ClassTool.isPrimitive(classz)) {
                    //只做String
                    if (!ClassTool.isString(classz)) {
                        continue;//不是string，跳
                    }

                    Sensitive sensitive = field.getAnnotation(Sensitive.class);
                    if (sensitive == null || sensitive.type() == SensitiveTypeEnum.NOSENSTIVE) {
                        continue;//此字段不脱敏
                    }
                    return true;
                    //做替换
                }
                if (Iterable.class.isAssignableFrom(classz)) {
                    Class<?> classGeneric = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                    Object valueObj = field.get(t); //得到该属性的get方法的值
                    if (valueObj == null) {
                        if (isSensitive(null, classGeneric, deep + 1)) {
                            return true;
                        }
                        if (ClassTool.isSuper(classGeneric.getSuperclass())) {
                            continue;
                        }
                        //处理父类
                        if (isSensitive(null, classGeneric.getSuperclass(), deep + 1)) {
                            return true;
                        }
                        continue;
                    }
                    Iterable values = (Iterable) valueObj;
                    for (final Object source : values) {
                        if (isSensitive(source, classGeneric, deep + 1)) {
                            return true;
                        }
                        //处理父类
                        if (isSensitive(source, classGeneric.getSuperclass(), deep + 1)) {
                            return true;
                        }
                        break;
                    }
                    continue;
                }
                if (classz.isArray()) {
                    Class<?> classArray = classz.getComponentType();
                    if (classArray == null) {
                        continue;
                    }
                    Object valueObj = field.get(t); //得到该属性的get方法的值
                    if (valueObj == null) {
                        if (isSensitive(null, classArray, deep + 1)) {
                            return true;
                        }
                        if (ClassTool.isSuper(classArray.getSuperclass())) {
                            continue;
                        }
                        if (isSensitive(null, classArray.getSuperclass(), deep + 1)) {
                            return true;
                        }
                    }
                    if (isSensitive(Array.get(valueObj, 0), classArray, deep + 1)) {
                        return true;
                    }
                    if (ClassTool.isSuper(classArray.getSuperclass())) {
                        continue;
                    }
                    //处理父类
                    if (isSensitive(Array.get(valueObj, 0), classArray.getSuperclass(), deep + 1)) {
                        return true;
                    }
                    continue;
                }
                if (Map.class.isAssignableFrom(classz)) {
                    continue;//map不处理
                }
                //处理泛型
                Type genericType = field.getGenericType();
                if (genericType != null && genericType.getTypeName().equals("T")) {
                    Object value = field.get(t);
                    if (value == null) {
                        continue;
                    }
                    Class<?> valueClass = value.getClass();
                    //list
                    if (Iterable.class.isAssignableFrom(valueClass)) {
                        Iterable values = (Iterable) value;
                        for (final Object source : values) {
                            Class<?> classGeneric = source.getClass();
                            if (isSensitive(source, classGeneric, deep + 1)) {
                                return true;
                            }
                            if (ClassTool.isSuper(classGeneric.getSuperclass())) {
                                continue;
                            }
                            //父类
                            if (isSensitive(source, classGeneric.getSuperclass(), deep + 1)) {
                                return true;
                            }
                            break;
                        }
                        continue;
                    }
                    //[]
                    if (valueClass.isArray()) {
                       Object valueArray= Array.get(value, 0);
                        if(valueArray==null)
                        {
                            continue;
                        }
                        Class<?> classArray = valueArray.getClass();
                        if (classArray == null) {
                            continue;
                        }

                        if (isSensitive(valueArray, classArray, deep + 1)) {
                            return true;
                        }
                        if (ClassTool.isSuper(classArray.getSuperclass())) {
                            continue;
                        }
                        //处理父类
                        if (isSensitive(valueArray, classArray.getSuperclass(), deep + 1)) {
                            return true;
                        }

                        continue;
                    }
                    if (Map.class.isAssignableFrom(valueClass)) {
                        continue;
                    }

                    if (isSensitive(value, value.getClass(), deep + 1)) {
                        return true;
                    }
                    if (ClassTool.isSuper(value.getClass().getSuperclass())) {
                        continue;
                    }
                    //处理父类
                    if (isSensitive(value, value.getClass().getSuperclass(), deep + 1)) {
                        return true;
                    }
                    continue;
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                return false;
            }
        }
        if (ClassTool.isSuper(classt.getSuperclass())) {
            //系统类不脱敏
            return false;
        }
        //处理父类
        return isSensitive(t, classt.getSuperclass(), deep + 1);
    }
}

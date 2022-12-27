package com.study.kubernete.user.sensitive;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 计算泛型唯一限定,List<User>生成List@User
 */
public class SensitiveGenericKey {
    /**
     * 只做一级，缓存限定名
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String buildGenericKeyName(T t) {
        StringBuilder keyName = new StringBuilder();

        try {
            Class<?> classt = t.getClass();
            if (Iterable.class.isAssignableFrom(classt)) {
                Iterable values = (Iterable) t;
                for (final Object source : values) {
                    Class<?> classGeneric = source.getClass();
                    keyName.append(classGeneric.getName());
                    return keyName.toString();
                }
            }
            if (classt.isArray()) {
                Class<?> classArray = classt.getComponentType();
                keyName.append(classArray.getName());
                return keyName.toString();
            }

            keyName.append(classt.getName());
            Field[] fields = classt.getDeclaredFields();//得到要修改的对象的属性数组
            if (fields == null || fields.length <= 0) {
                return keyName.toString();
            }

            for (Field field : fields) {
                field.setAccessible(true);
                Class<?> classz = field.getType();

                if (ClassTool.isPrimitive(classz)) {
                    continue;
                }
                if (classz.isArray()) {
                    continue;
                }
                if (Iterable.class.isAssignableFrom(classz)) {
                    continue;
                }
                if (Map.class.isAssignableFrom(classz)) {
                    continue;
                }
                //处理泛型的List,[],实体三种情况
                Type genericType = field.getGenericType();//对泛型操作，通过值反射。如值为空呢。
                if (genericType.getTypeName().equals("T")) {
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
                            keyName.append("@" +classGeneric.getName());
                            break;
                        }
                        continue;
                    }
                    //[]
                    if (valueClass.isArray()) {
                        Object valueArray=  Array.get(value, 0);
                        Class<?> classArray = valueArray.getClass();
                        keyName.append("@" +classArray.getName());
                        continue;
                    }
                    if (Map.class.isAssignableFrom(valueClass)) {
                        continue;
                    }
                    //实体
                    keyName.append("@" + valueClass.getName());
                    continue;
                }
            }
            return keyName.toString();
        } catch (Exception ex) {
            return keyName.toString();
        }

    }
}

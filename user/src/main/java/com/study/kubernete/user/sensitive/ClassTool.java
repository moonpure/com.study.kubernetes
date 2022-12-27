package com.study.kubernete.user.sensitive;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ClassTool {

    private static final Map<Class<?>, Class<?>> primitiveMap = new HashMap<>(9);

    static {
        primitiveMap.put(String.class, String.class);
        primitiveMap.put(Boolean.class, boolean.class);
        primitiveMap.put(Byte.class, byte.class);
        primitiveMap.put(Character.class, char.class);
        primitiveMap.put(Double.class, double.class);
        primitiveMap.put(Float.class, float.class);
        primitiveMap.put(Integer.class, int.class);
        primitiveMap.put(Long.class, long.class);
        primitiveMap.put(Short.class, short.class);
        primitiveMap.put(Date.class, Date.class);
    }

    /**
     * @description 判断基本类型

     * @param clazz
     * @return boolean
     */
    public static boolean isPrimitive(Class<?> clazz) {
        if (primitiveMap.containsKey(clazz)) {
            return true;
        }
        if (clazz.getPackage() != null) {
            return clazz.isPrimitive() || clazz.isEnum();
        }
        return false;
    }
    public static boolean isSuper(Class<?> clazz) {
        if (primitiveMap.containsKey(clazz)) {
            return true;
        }
        if (clazz.getPackage() != null) {
            return clazz.isPrimitive() || clazz.isEnum() || clazz.getName().startsWith("java.") || clazz.getName().startsWith("javax.") ||clazz.getPackage().getName().startsWith("java.") || clazz.getPackage().getName().startsWith("javax.");
        }
        return false;
    }

    /**
     * 只有String做，
     * @param clazz
     * @return
     */
    public static boolean isString(Class<?> clazz) {
       if(clazz==null)
       {
           return false;
       }
       String typeName=clazz.getSimpleName();
       return typeName.equals("String");
    }

    /**
     * @description 获取方法返回值类型
     * @param tartget
     * @param fieldName
     * @return
     * @return Class<?>
     */
    public static Class<?> getElementType(Class<?> tartget, String fieldName) {
        Class<?> elementTypeClass = null;
        try {
            Type type = tartget.getDeclaredField(fieldName).getGenericType();
            ParameterizedType t = (ParameterizedType) type;
            String classStr = t.getActualTypeArguments()[0].toString().replace("class ", "");
            elementTypeClass = Thread.currentThread().getContextClassLoader().loadClass(classStr);
        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException e) {
         log.error(e.getMessage());
        }
        return elementTypeClass;
    }

}
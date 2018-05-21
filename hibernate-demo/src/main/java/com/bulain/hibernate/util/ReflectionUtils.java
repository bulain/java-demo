package com.bulain.hibernate.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class ReflectionUtils extends org.springframework.util.ReflectionUtils{
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

    public static <T> Class<T> getSuperClassGenricType(final Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    public static Class getSuperClassGenricType(final Class clazz, final int index) {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            logger.warn("{}'s superclass not ParameterizedType", clazz.getSimpleName());
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            logger.warn("Index: {}, Size of {}'s Parameterized Type: {}" + index, clazz.getSimpleName(), params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            logger.warn("{} not set the actual class on superclass generic parameter", clazz.getSimpleName());
            return Object.class;
        }

        return (Class) params[index];
    }

    public static Object getFieldValue(final Object target, final String fieldName) {
        Assert.notNull(target, "Target object must not be null");
        
        Field field = ReflectionUtils.findField(target.getClass(), fieldName);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + target + "]");
        }

        logger.debug("Getting field [" + fieldName + "] from target [" + target + "]");

        ReflectionUtils.makeAccessible(field);
        return ReflectionUtils.getField(field, target);
    }

    public static void setFieldValue(final Object target, final String fieldName, final Object value) {
        Assert.notNull(target, "Target object must not be null");
        
        Field field = ReflectionUtils.findField(target.getClass(), fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + target + "]");
        }

        logger.debug("Setting field [" + fieldName + "] on target [" + target + "]");
        
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, target, value);
    }

}

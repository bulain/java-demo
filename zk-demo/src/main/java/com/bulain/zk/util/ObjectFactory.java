package com.bulain.zk.util;

import java.util.Map;

import ognl.OgnlException;

import com.bulain.zk.validator.validators.FieldValidator;

public class ObjectFactory {
    
    public static FieldValidator buildValidator(String className, Map<String, String> params)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, OgnlException {
        FieldValidator instance = (FieldValidator) buildBean(className);
        OgnlUtil.setProperties(params, instance);
        return instance;
    }

    public static Object buildBean(String className) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException {
        Class<?> clazz = getClassInstance(className);
        return buildBean(clazz);
    }

    public static Object buildBean(Class<?> clazz) throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }

    public static Class<?> getClassInstance(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }
}

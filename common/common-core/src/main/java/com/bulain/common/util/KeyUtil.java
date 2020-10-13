package com.bulain.common.util;

public final class KeyUtil {
    private static final String COMA = "_";

    private KeyUtil() {
    }

    public static String getSessionKey(Class<?> actionClz, Class<?> modelClz) {
        return getShortClassName(actionClz) + COMA + getShortClassName(modelClz);
    }

    public static String getCacheKey(Class<?> clz) {
        return getShortClassName(clz);
    }

    public static String getShortClassName(Class<?> clz) {
        return clz.getSimpleName();
    }
}

package com.bulain.db.base;

import org.springframework.context.ApplicationContext;

public final class ContextHolder {
    private static ApplicationContext applicationContext;

    private ContextHolder() {
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext appContext) {
        applicationContext = appContext;
    }
}

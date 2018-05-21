package com.bulain.common.interceptor;

import java.util.List;
import java.util.Locale;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContext;

public class ContextUtil {
    private static final ThreadLocal<RequestContext> threadLocal = new ThreadLocal<RequestContext>();

    public static void setRequestContext(RequestContext context) {
        threadLocal.set(context);
    }

    public static RequestContext getRequestContext() {
        return threadLocal.get();
    }

    public static WebApplicationContext getWebApplicationContext() {
        RequestContext context = getRequestContext();
        return context.getWebApplicationContext();
    }

    public static Object getBean(String name) {
        WebApplicationContext applicationContext = getWebApplicationContext();
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        WebApplicationContext applicationContext = getWebApplicationContext();
        return applicationContext.getBean(name, requiredType);
    }

    public static Locale getLocale() {
        RequestContext context = getRequestContext();
        return context.getLocale();
    }

    public static String getMessage(String code) {
        RequestContext context = getRequestContext();
        return context.getMessage(code);
    }

    public static String getMessage(String code, Object[] args) {
        RequestContext context = getRequestContext();
        return context.getMessage(code, args);
    }

    public static String getMessage(String code, List<Object> args) {
        RequestContext context = getRequestContext();
        return context.getMessage(code, args);
    }

}

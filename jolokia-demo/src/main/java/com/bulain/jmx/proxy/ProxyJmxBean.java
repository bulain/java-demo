package com.bulain.jmx.proxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.DynamicMBean;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.util.ReflectionUtils;

public class ProxyJmxBean implements DynamicMBean {
    private Object target;

    public ProxyJmxBean(Object jmxBean) {
        target = jmxBean;
    }

    /* jmx */
    public Object getAttribute(String attribute) {

        Field field = ReflectionUtils.findField(target.getClass(), attribute);
        Class<?> type = field.getType();
        String methodName = "get" + attribute.substring(0, 1).toUpperCase()
                + (attribute.length() > 1 ? attribute.substring(1) : "");
        if (type.equals(boolean.class) || type.equals(Boolean.class)) {
            String isMethodName = "is" + attribute.substring(0, 1).toUpperCase()
                    + (attribute.length() > 1 ? attribute.substring(1) : "");
            Method method = ReflectionUtils.findMethod(target.getClass(), isMethodName);
            if (method != null) {
                methodName = isMethodName;
            }
        }

        Method method = ReflectionUtils.findMethod(target.getClass(), methodName);
        return ReflectionUtils.invokeMethod(method, target);

        /*
        Field field = ReflectionUtils.findField(target.getClass(), attribute);
        ReflectionUtils.makeAccessible(field);
        return ReflectionUtils.getField(field, target);
        */
    }

    public void setAttribute(Attribute attribute) {

        String name = attribute.getName();
        Object value = attribute.getValue();
        String methodName = "set" + name.substring(0, 1).toUpperCase() + (name.length() > 1 ? name.substring(1) : "");
        Field field = ReflectionUtils.findField(target.getClass(), name);
        Class<?> type = field.getType();
        Method method = ReflectionUtils.findMethod(target.getClass(), methodName, new Class[]{type});
        ReflectionUtils.invokeMethod(method, target, new Object[]{value});

        /*
        Field field = ReflectionUtils.findField(target.getClass(), attribute.getName());
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, target, attribute.getValue());
        */
    }

    public AttributeList getAttributes(String[] attributes) {
        AttributeList list = new AttributeList();
        for (String attribute : attributes) {
            list.add(getAttribute(attribute));
        }
        return list;
    }

    public AttributeList setAttributes(AttributeList attributes) {
        AttributeList list = new AttributeList();
        for (Attribute attribute : attributes.asList()) {
            setAttribute(attribute);
            list.add(attribute);
        }
        return list;
    }

    public Object invoke(String actionName, Object[] params, String[] signature) {
        List<Class<?>> listType = new ArrayList<Class<?>>();
        for (Object obj : params) {
            listType.add(obj.getClass());
        }
        Method method = ReflectionUtils.findMethod(target.getClass(), actionName, listType.toArray(new Class[0]));
        return ReflectionUtils.invokeMethod(method, target, params);
    }

    public MBeanInfo getMBeanInfo() {
        Map<String, MBeanAttr> mapAttrs = new HashMap<String, MBeanAttr>();
        List<MBeanOperationInfo> listOper = new ArrayList<MBeanOperationInfo>();
        Method[] declaredMethods = ReflectionUtils.getAllDeclaredMethods(target.getClass());
        for (Method method : declaredMethods) {
            //ManagedAttribute
            ManagedAttribute ann = AnnotationUtils.findAnnotation(method, ManagedAttribute.class);
            if (ann != null) {
                String name = method.getName();
                int len = name.startsWith("is") ? 2 : 3;
                String key = name.substring(len);
                key = key.substring(0, 1).toLowerCase() + (key.length() > 1 ? key.substring(1) : "");

                MBeanAttr ma = mapAttrs.get(key);
                if (ma == null) {
                    Field field = ReflectionUtils.findField(target.getClass(), key);
                    ma = new MBeanAttr(key, field.getType().getName(), "", false, false, false);
                    mapAttrs.put(key, ma);
                }

                if (name.startsWith("get")) {
                    ma.readable = true;
                }
                if (name.startsWith("set")) {
                    ma.writable = true;
                }
                if (name.startsWith("is")) {
                    ma.is = true;
                    ma.readable = true;
                }
            }

            //ManagedOperation
            ManagedOperation oper = AnnotationUtils.findAnnotation(method, ManagedOperation.class);
            if (oper != null) {
                String name = method.getName();
                String description = oper.description();
                Class<?>[] parameterTypes = method.getParameterTypes();
                Class<?> returnType = method.getReturnType();

                ManagedOperationParameters parameters = AnnotationUtils.findAnnotation(method,
                        ManagedOperationParameters.class);
                List<MBeanParameterInfo> listParam = new ArrayList<MBeanParameterInfo>();
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> xx = parameterTypes[i];
                    String paramName = parameters.value()[i].name();
                    String paramDesc = parameters.value()[i].description();
                    String type = xx.getName();
                    MBeanParameterInfo param = new MBeanParameterInfo(paramName, type, paramDesc);
                    listParam.add(param);
                }

                MBeanOperationInfo mBeanOperationInfo = new MBeanOperationInfo(name, description,
                        listParam.toArray(new MBeanParameterInfo[0]), returnType.toString(), MBeanOperationInfo.ACTION);
                listOper.add(mBeanOperationInfo);
            }
        }

        List<MBeanAttributeInfo> listAttr = new ArrayList<MBeanAttributeInfo>();
        for (Entry<String, MBeanAttr> entry : mapAttrs.entrySet()) {
            String key = entry.getKey();
            MBeanAttr attr = entry.getValue();
            MBeanAttributeInfo mBeanAttributeInfo = new MBeanAttributeInfo(key, attr.type, attr.description,
                    attr.readable, attr.writable, attr.is);
            listAttr.add(mBeanAttributeInfo);
        }

        //ManagedResource
        ManagedResource resource = AnnotationUtils.findAnnotation(target.getClass(), ManagedResource.class);
        String description = resource.description();

        return new MBeanInfo(target.getClass().getName(), description, listAttr.toArray(new MBeanAttributeInfo[0]),
                null, listOper.toArray(new MBeanOperationInfo[0]), null);

    }

    static class MBeanAttr {
        String name;
        String type;
        String description;
        boolean readable;
        boolean writable;
        boolean is;

        MBeanAttr(String name, String type, String description, boolean readable, boolean writable, boolean is) {
            this.name = name;
            this.type = type;
            this.description = description;
            this.readable = readable;
            this.writable = writable;
            this.is = is;
        }

    }
}
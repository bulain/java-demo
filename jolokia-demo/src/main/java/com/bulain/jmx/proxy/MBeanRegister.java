package com.bulain.jmx.proxy;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jmx.export.annotation.ManagedResource;

import com.bulain.jmx.mbean.WorkBean;

public class MBeanRegister {
    public static void registerMBean(WorkBean bean) throws MalformedObjectNameException,
            InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ProxyJmxBean m = new ProxyJmxBean(bean);
        ManagedResource resource = AnnotationUtils.findAnnotation(bean.getClass(), ManagedResource.class);
        String objectName = resource.objectName();
        ObjectName name = new ObjectName(objectName);
        mbs.registerMBean(m, name);
    }
}

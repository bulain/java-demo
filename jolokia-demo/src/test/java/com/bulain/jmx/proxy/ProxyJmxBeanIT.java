package com.bulain.jmx.proxy;

import com.bulain.jmx.mbean.WorkBean;
import com.bulain.jmx.proxy.MBeanRegister;

public class ProxyJmxBeanIT {

    public static void main(String[] args) {
        try {
            WorkBean bean = new WorkBean();
            MBeanRegister.registerMBean(bean);
            
            System.out.println("Click any key to exit...");
            System.in.read();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

}

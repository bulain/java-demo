package com.bulain.log;

import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.logging.LogManager;

//-Djava.util.logging.config.class=com.bulain.log.InitJdkLog
public class InitJdkLog {
    static {
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
                public Object run() throws Exception {
                    System.out.println("read configuration from classpath:logging.properties");
                    ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                    InputStream ins = contextClassLoader.getResourceAsStream("logging.properties");
                    LogManager logManager = LogManager.getLogManager();
                    logManager.readConfiguration(ins);
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

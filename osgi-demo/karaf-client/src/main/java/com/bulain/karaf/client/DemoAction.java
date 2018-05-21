package com.bulain.karaf.client;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.karaf.service.DemoService;

public class DemoAction {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private DemoService demoService;

    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }

    public void start() {
        try {
            String hello = demoService.sayHello("world");
            logger.info("start() - [" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + hello);
        } catch (Exception e) {
            logger.error("start()", e);
        }
    }

}
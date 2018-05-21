package com.bulain.karaf.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.karaf.service.KarafService;

public class KarafServiceImpl implements KarafService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public String sayHello(String name) {
        logger.info("sayHello(String) - [" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name);
        return "Hello " + name + ", response from provider";
    }

    public void init() {
        logger.info("DemoServiceImpl初始化");
    }

}
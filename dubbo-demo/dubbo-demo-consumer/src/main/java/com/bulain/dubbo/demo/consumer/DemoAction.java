package com.bulain.dubbo.demo.consumer;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bulain.dubbo.demo.DemoService;

public class DemoAction {
    private static final Logger logger = Logger.getLogger(DemoAction.class);

    private DemoService demoService;

    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }

    public void start() throws Exception {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                String hello = demoService.sayHello("world" + i);
                logger.info("start() - [" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + hello);
                
                //Future<Object> future = RpcContext.getContext().getFuture();
                //Object obj = future.get();
                //logger.info("future() - [" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + obj);
                
            } catch (Exception e) {
                logger.error("start()", e);
            }
            Thread.sleep(2000);
        }
    }

}
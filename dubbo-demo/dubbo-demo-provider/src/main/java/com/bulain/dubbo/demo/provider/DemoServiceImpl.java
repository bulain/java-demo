package com.bulain.dubbo.demo.provider;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bulain.dubbo.demo.DemoService;

public class DemoServiceImpl implements DemoService {
    private static final Logger logger = Logger.getLogger(DemoServiceImpl.class);

    public String sayHello(String name) {
        logger.info("sayHello(String) - [" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name
                + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "Hello " + name + ", response form provider: " + RpcContext.getContext().getLocalAddress();
    }

}
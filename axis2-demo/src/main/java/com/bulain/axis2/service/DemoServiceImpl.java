package com.bulain.axis2.service;

import com.bulain.axis2.pojo._1_0.Demo;
import com.bulain.axis2.service.demo.DemoServiceSkeletonInterface;

public class DemoServiceImpl implements DemoServiceSkeletonInterface {

    public String test01(String request) {
        return request;
    }

    public Demo test02(Demo request) {
        request.setResponse("response");
        return request;
    }

}

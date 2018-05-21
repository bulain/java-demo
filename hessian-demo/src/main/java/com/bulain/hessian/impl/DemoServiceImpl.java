package com.bulain.hessian.impl;

import com.bulain.hessian.api.DemoService;
import com.bulain.hessian.pojo.Demo;

public class DemoServiceImpl implements DemoService {

    @Override
    public Demo message(Demo demo) {
        return demo;
    }

}

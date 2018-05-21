package com.bulain.service;

import com.bulain.pojo.HelloReq;
import com.bulain.pojo.HelloResp;

public class DemoServiceImpl implements DemoService{
    
    public HelloResp hello(HelloReq req){
        return new HelloResp();
    }
    
}

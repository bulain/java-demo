package com.bulain.service;

import com.bulain.pojo.HelloReq;
import com.bulain.pojo.HelloResp;

public interface DemoService {
    
    HelloResp hello(HelloReq req);
    
}

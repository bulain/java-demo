package com.bulain.impl;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

import com.bulain.pojo.HelloReq;
import com.bulain.pojo.HelloResp;
import com.bulain.service.DemoService;
import com.bulain.ws.DemoWs;

public class DemoWsImpl extends ServletEndpointSupport implements DemoWs {

    private DemoService demoService;
    
    @Override
    protected void onInit() throws ServiceException {
        demoService = getApplicationContext().getBean(DemoService.class);
    }

    public HelloResp hello(HelloReq helloReq) throws RemoteException {
        return demoService.hello(helloReq);
    }

}

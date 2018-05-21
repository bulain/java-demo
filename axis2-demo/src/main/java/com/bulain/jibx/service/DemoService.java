package com.bulain.jibx.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.bulain.jibx.pojo.Demo;

@WebService(name = "DemoService", targetNamespace = "http://bulain.com/axis2/service/DemoService/1.0")
public class DemoService {

    @WebMethod
    @WebResult(name = "response")
    public String test01(@WebParam(name = "request") String request) {
        return null;
    }

    @WebMethod
    @WebResult(name = "response")
    public Demo test02(@WebParam(name = "request") Demo request) {
        return null;
    }

}

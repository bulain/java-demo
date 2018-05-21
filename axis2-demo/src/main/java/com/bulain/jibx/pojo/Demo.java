package com.bulain.jibx.pojo;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "demo", namespace = "http://bulain.com/axis2/demo/1.0")
public class Demo {
    private String request;
    private String response;

    public String getRequest() {
        return request;
    }
    public void setRequest(String request) {
        this.request = request;
    }
    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }
}

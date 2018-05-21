package com.bulain.jibx.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="message")
public class Message {
    private Request request;
    private Response response;
    
    public Request getRequest() {
        return request;
    }
    public void setRequest(Request request) {
        this.request = request;
    }
    public Response getResponse() {
        return response;
    }
    public void setResponse(Response response) {
        this.response = response;
    }
    
}

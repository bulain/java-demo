package com.bulain.cxf.jarws;

public class HelloServiceImpl implements HelloService {

    @Override
    public String say(String hello) {
        return "hello " + hello;
    }

    @Override
    public Hello handle(String request) {
        Hello hello  = new Hello();
        hello.setRequest(request);
        hello.setResponse("Hello " +  request);
        return hello;
    }

    @Override
    public Hello hand(Hello hello) {
        hello.setResponse("Hello " +  hello.getRequest());
        return hello;
    }

}

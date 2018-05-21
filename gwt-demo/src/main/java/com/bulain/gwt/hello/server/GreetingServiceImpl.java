package com.bulain.gwt.hello.server;

import com.bulain.gwt.hello.client.GreetingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
    private static final long serialVersionUID = -2510671214214316530L;

    public String greetServer(String input) {
        return "Hello, " + input + "!";
    }

}

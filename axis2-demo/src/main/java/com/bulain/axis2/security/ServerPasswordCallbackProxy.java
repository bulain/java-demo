package com.bulain.axis2.security;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.axis2.extensions.spring.receivers.ApplicationContextHolder;
import org.springframework.context.ApplicationContext;

public class ServerPasswordCallbackProxy implements CallbackHandler {

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        ApplicationContext context = ApplicationContextHolder.getContext();
        CallbackHandler callbackHandler = (CallbackHandler) context.getBean("serverPasswordCallback");
        callbackHandler.handle(callbacks);
    }

}

package com.bulain.gwt.hello.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class HelloWorld implements EntryPoint {

    public void onModuleLoad() {
        final GreetingServiceAsync service = GWT.create(GreetingService.class);

        IButton button = new IButton("Hello World");
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                AsyncCallback<String> callback = new AsyncCallback<String>() {
                    public void onSuccess(String msg) {
                        SC.say(msg);
                    }

                    public void onFailure(Throwable e) {
                        SC.say(e.toString());
                    }
                };
                service.greetServer("Bulain", callback);
            }
        });

        button.draw();
    }
}
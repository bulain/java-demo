package com.bulain.jmx.mbean;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(objectName = "com.bulain.jmx:type=WorkBean", description = "This is Jmx Management Bean")
public class WorkBean{

    private boolean usingProxy = false;
    private String username;

    @ManagedAttribute
    @ManagedOperation(description = "Get User Name of WorkBean")
    public String getUsername() {
        return username;
    }

    @ManagedAttribute
    @ManagedOperation(description = "Set User Name of WorkBean")
    public void setUsername(String username) {
        this.username = username;
    }

    @ManagedAttribute
    public boolean isUsingProxy() {
        return usingProxy;
    }

    @ManagedAttribute
    public void setUsingProxy(boolean usingProxy) {
        this.usingProxy = usingProxy;
    }

    @ManagedOperation(description = "Enable Proxy")
    public void enableProxy() {
        this.usingProxy = true;
    }

    @ManagedOperation(description = "Disable Proxy")
    public void disableProxy() {
        this.usingProxy = false;
    }
}

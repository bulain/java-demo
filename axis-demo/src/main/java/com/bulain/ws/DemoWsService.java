/**
 * DemoWsService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bulain.ws;

public interface DemoWsService extends javax.xml.rpc.Service {
    public java.lang.String getDemoWsAddress();

    public com.bulain.ws.DemoWs getDemoWs() throws javax.xml.rpc.ServiceException;

    public com.bulain.ws.DemoWs getDemoWs(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}

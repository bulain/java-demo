/**
 * DemoWsServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bulain.ws;

public class DemoWsServiceLocator extends org.apache.axis.client.Service implements com.bulain.ws.DemoWsService {

    public DemoWsServiceLocator() {
    }


    public DemoWsServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DemoWsServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DemoWs
    private java.lang.String DemoWs_address = "http://localhost:8080/axis/services/DemoWS";

    public java.lang.String getDemoWsAddress() {
        return DemoWs_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DemoWsWSDDServiceName = "DemoWs";

    public java.lang.String getDemoWsWSDDServiceName() {
        return DemoWsWSDDServiceName;
    }

    public void setDemoWsWSDDServiceName(java.lang.String name) {
        DemoWsWSDDServiceName = name;
    }

    public com.bulain.ws.DemoWs getDemoWs() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DemoWs_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDemoWs(endpoint);
    }

    public com.bulain.ws.DemoWs getDemoWs(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.bulain.ws.DemoWsSoapBindingStub _stub = new com.bulain.ws.DemoWsSoapBindingStub(portAddress, this);
            _stub.setPortName(getDemoWsWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDemoWsEndpointAddress(java.lang.String address) {
        DemoWs_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.bulain.ws.DemoWs.class.isAssignableFrom(serviceEndpointInterface)) {
                com.bulain.ws.DemoWsSoapBindingStub _stub = new com.bulain.ws.DemoWsSoapBindingStub(new java.net.URL(DemoWs_address), this);
                _stub.setPortName(getDemoWsWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("DemoWs".equals(inputPortName)) {
            return getDemoWs();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.bulain.com", "DemoWsService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.bulain.com", "DemoWs"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DemoWs".equals(portName)) {
            setDemoWsEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

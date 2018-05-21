/**
 * DemoWsServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bulain.ws;

public class DemoWsServiceTestCase extends junit.framework.TestCase {
    public DemoWsServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void testDemoWsWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new com.bulain.ws.DemoWsServiceLocator().getDemoWsAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new com.bulain.ws.DemoWsServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1DemoWsHello() throws Exception {
        com.bulain.ws.DemoWsSoapBindingStub binding;
        try {
            binding = (com.bulain.ws.DemoWsSoapBindingStub)
                          new com.bulain.ws.DemoWsServiceLocator().getDemoWs();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        com.bulain.pojo.HelloResp value = null;
        value = binding.hello(new com.bulain.pojo.HelloReq());
        // TBD - validate results
    }

}

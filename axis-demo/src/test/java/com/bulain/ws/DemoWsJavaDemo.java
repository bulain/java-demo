package com.bulain.ws;

import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.junit.Before;
import org.junit.Test;

import com.bulain.pojo.HelloReq;
import com.bulain.pojo.HelloResp;

public class DemoWsJavaDemo {

    private DemoWs demoWs;

    @Before
    public void setUp() throws ServiceException, MalformedURLException {
        DemoWsService locator = new DemoWsServiceLocator();
        URL url = new URL("http://localhost:8081/axis-demo/services/DemoWs");
        demoWs = locator.getDemoWs(url);
    }

    @Test
    public void testHello() throws RemoteException {
        HelloReq helloReq = new HelloReq();
        HelloResp resp = demoWs.hello(helloReq);
        assertNotNull(resp);
    }

}

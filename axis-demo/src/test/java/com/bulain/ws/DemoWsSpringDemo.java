package com.bulain.ws;

import static org.junit.Assert.assertNotNull;

import java.rmi.RemoteException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.bulain.pojo.HelloReq;
import com.bulain.pojo.HelloResp;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class DemoWsSpringDemo {

    @Autowired
    private DemoWs demoWs;

    @Test
    public void testHello() throws RemoteException {
        HelloReq helloReq = new HelloReq();
        HelloResp resp = demoWs.hello(helloReq);
        assertNotNull(resp);
    }

}

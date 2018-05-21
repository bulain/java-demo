package com.bulain.hessian.api;

import static org.junit.Assert.*;
import java.net.MalformedURLException;
import java.util.Date;

import org.junit.Test;

import com.bulain.hessian.pojo.Demo;
import com.caucho.hessian.client.HessianProxyFactory;

public class DemoServiceDemo {

    @Test
    public void testMessage() throws MalformedURLException {
        String url = "http://localhost:8086/hessian-demo/remote/demoService";
        HessianProxyFactory factory = new HessianProxyFactory();
        DemoService demoService = (DemoService) factory.create(DemoService.class, url);

        Demo req = new Demo();
        req.setName("bulain");
        req.setAge(30);
        req.setCreateAt(new Date());
        Demo resp = demoService.message(req);

        assertNotNull(resp);
        assertEquals(req.getName(), resp.getName());
        assertEquals(req.getAge(), resp.getAge());
    }

}

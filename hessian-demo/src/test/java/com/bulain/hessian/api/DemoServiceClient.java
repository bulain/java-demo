package com.bulain.hessian.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bulain.hessian.pojo.Demo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-test/applicationContext-*.xml"})
public class DemoServiceClient {

    @Autowired
    private DemoService demoService;

    @Test
    public void testMessage() {
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

package com.bulain.camel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/springContext-jms.xml"})
public class SpringJms2FileDemo {
    
    @Test
    public void testJms() throws InterruptedException {
        Thread.sleep(10000);
    }
}

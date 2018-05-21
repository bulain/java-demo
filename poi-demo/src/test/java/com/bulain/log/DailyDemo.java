package com.bulain.log;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class DailyDemo {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testDaily() {
        while (true) {
            logger.debug("testDaily()-{}", new Date().getTime());
            try {
                Thread.sleep(100L);
            } catch (Exception e) {
                logger.error("testDaily()-", e);
            }
        }
    }

}

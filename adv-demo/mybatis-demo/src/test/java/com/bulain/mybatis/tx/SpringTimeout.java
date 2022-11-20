package com.bulain.mybatis.tx;

import com.bulain.mybatis.demo.model.Blog;
import com.bulain.mybatis.demo.service.BlogService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.sql.SQLException;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class SpringTimeout {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BlogService blogService;

    private Date startAt;

    @BeforeEach
    public void setUp() {
        startAt = new Date();
        logger.info("setUp");
    }

    @AfterEach
    public void tearDown() {
        Date endAt = new Date();
        double during = (endAt.getTime() - startAt.getTime()) / 1000d;
        logger.info("tearDown during {}s", new Object[]{during});
    }

    @Test
    public void testTimeoutAfter() throws InterruptedException, SQLException {
        Blog data = new Blog();
        data.setTitle("abd");
        data.setDescr("descr");
        data.setCreatedVia("Thread");
        data.setActiveFlag("Y");
        data.setCreatedAt(new Date());
        data.setCreatedBy("PT");
        blogService.insert(data, true);

    }
    
    @Test
    public void testTimeoutBefore() throws InterruptedException, SQLException {
        Blog data = new Blog();
        data.setId(1L);
        data.setTitle("abd");
        data.setDescr("descr");
        data.setCreatedVia("Thread");
        data.setActiveFlag("Y");
        data.setCreatedAt(new Date());
        data.setCreatedBy("PT");
        blogService.update(data, true);
    }

}

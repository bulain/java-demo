package com.bulain.mybatis.demo.service;


import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.bulain.mybatis.demo.model.Blog;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class BlogServiceDemo {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private BlogService blogService;

    @Test
    public void testInsert() {
        Blog data = new Blog();
        data.setTitle("abd");
        data.setDescr("descr");
        data.setCreatedVia("Thread");
        data.setActiveFlag("Y");
        data.setCreatedAt(new Date());
        data.setCreatedBy("PT");
        blogService.insert(data, true);
        
        assertNotNull(data.getId());
        
        logger.debug("ID:{}", data.getId());
    }

}

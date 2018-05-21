package com.bulain.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml",
        "classpath*:spring/propertyConfigurer-test.xml"})
public class AnnotationTest {
    
    @Autowired
    private ShiroService shiroService;
    private Subject subject;
    
    @Before
    public void setUp(){
        subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken("user1", "password1".toCharArray());
        subject.login(token);
    }
    
    @After
    public void tearDown(){
        subject.logout();
    }
    
    @Test
    public void testCallByAuth() {
        shiroService.callByAuth();
    }

    @Test
    public void testCallByRole() {
        shiroService.callByRole();
    }

    @Test
    public void testCallByPerm() {
        shiroService.callByPerm();
    }
}

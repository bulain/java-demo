package com.bulain.shiro;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml",
        "classpath*:spring/propertyConfigurer-test.xml"})
public class SpringTest {

    @Test
    public void testShiro() {
        //get subject
        Subject subject = SecurityUtils.getSubject();

        //login
        AuthenticationToken token = new UsernamePasswordToken("user1", "password1".toCharArray());
        subject.login(token);

        //has role role1
        boolean hasRole = subject.hasRole("role1");
        assertTrue(hasRole);

        //has not role role2
        hasRole = subject.hasRole("role2");
        assertFalse(hasRole);

        //has permission perm1
        boolean permitted = subject.isPermitted("perm1");
        assertTrue(permitted);

        //has not permission perm3
        permitted = subject.isPermitted("perm3");
        assertFalse(permitted);

        //has permission a1:b1
        permitted = subject.isPermitted("a1:b1");
        assertTrue(permitted);

        //has permission a2:b2:c2
        permitted = subject.isPermitted("a2:b2:c2");
        assertTrue(permitted);

        //logout
        subject.logout();
    }
}

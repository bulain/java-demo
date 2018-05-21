package com.bulain.shiro;

import static org.junit.Assert.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class ShiroTest {
    @Test
    public void testShiro(){
        //init securityManager
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.properties");
        SecurityManager securityManager = factory.createInstance();
        
        //put into singleton
        SecurityUtils.setSecurityManager(securityManager);
        
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

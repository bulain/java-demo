package com.bulain.jbpm4order.controller;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.model.Constants;
import com.bulain.common.model.Login;
import com.bulain.common.pojo.LoginSearch;
import com.bulain.common.service.LoginService;
import com.bulain.common.test.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;

public class AuthenticateActionTest extends ActionTestCase {
    @Autowired
    private LoginService loginService;

    private Long loginId;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        setUpLogin();
    }

    @After
    public void tearDown() throws Exception {
        tearDownLogin();
        super.tearDown();
    }

    private void setUpLogin() {
        Login login = new Login();
        login.setLoginName("loginName");
        login.setEmail("email@email.com");
        login.setHashedPassword("hashedPassword");
        login.setEnabled("enabled");

        loginService.insert(login);

        LoginSearch search = new LoginSearch();
        search.setLoginName("loginName");
        List<Login> listLogin = loginService.find(search);
        loginId = listLogin.get(0).getId();
    }

    private void tearDownLogin() {
        loginService.delete(loginId);
    }

    @Test
    public void testLogonAndLogout() throws Exception {
        initServletMockObjects();
        ActionProxy proxy = getActionProxy("/authenticate/login");
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        Map<String, Object> session = ActionContext.getContext().getSession();
        assertNull(session.get(Constants.SESSION_KEY_LOGIN));

        initServletMockObjects();
        request.setParameter("login.loginName", "loginName");
        request.setParameter("login.hashedPassword", "hashedPassword");
        proxy = getActionProxy("/authenticate/logon");
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        session = ActionContext.getContext().getSession();
        assertNotNull(session.get(Constants.SESSION_KEY_LOGIN));

        initServletMockObjects();
        proxy = getActionProxy("/authenticate/logout");
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        session = ActionContext.getContext().getSession();
        assertNull(session.get(Constants.SESSION_KEY_LOGIN));
    }
}

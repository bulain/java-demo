package com.bulain.jbpm4order.controller;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.model.Authorize;
import com.bulain.common.page.Page;
import com.bulain.common.test.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

@SeedDataSet(file = "data/init_action.xml")
@DataSet(file = "test-data/init_authorizes.xml")
public class AuthorizeActionTest extends ActionTestCase {
    @Before
    public void setUp() throws Exception {
        super.setUp();
        super.setUpAction("admin", "admin");
    }

    @After
    public void tearDown() throws Exception {
        super.tearDownAction();
        super.tearDown();
    }

    @Test
    public void testList() throws Exception {
        initServletMockObjects();
        request.setParameter("search.controller", "controller_page");
        request.setParameter("search.action", "action_page");

        ActionProxy proxy = getActionProxy("/authorize/list");
        AuthorizeAction authorizeAction = (AuthorizeAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        List<Authorize> listAuthorize = authorizeAction.getListAuthorize();
        assertEquals(3, listAuthorize.size());
        Page page = authorizeAction.getPage();
        assertEquals(1, page.getPage());
    }

    @Test
    public void testNewn() throws Exception {
        initServletMockObjects();

        ActionProxy proxy = getActionProxy("/authorize/new");
        proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

    @Test
    public void testCreate() throws Exception {
        initServletMockObjects();
        request.setParameter("authorize.controller", "controller");
        request.setParameter("authorize.action", "action");
        request.setParameter("authorize.permission", "permission");

        ActionProxy proxy = getActionProxy("/authorize/create");
        proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

    @Test
    public void testShow() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "102");

        ActionProxy proxy = getActionProxy("/authorize/show");
        AuthorizeAction authorizeAction = (AuthorizeAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Authorize authorize = authorizeAction.getAuthorize();
        assertEquals("controller_102", authorize.getController());
        assertEquals("action_102", authorize.getAction());
        assertEquals("permission_102", authorize.getPermission());
    }

    @Test
    public void testEdit() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "103");

        ActionProxy proxy = getActionProxy("/authorize/show");
        AuthorizeAction authorizeAction = (AuthorizeAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Authorize authorize = authorizeAction.getAuthorize();
        assertEquals("controller_103", authorize.getController());
        assertEquals("action_103", authorize.getAction());
        assertEquals("permission_103", authorize.getPermission());
    }

    @Test
    public void testUpdate() throws Exception {
        initServletMockObjects();
        request.setParameter("authorize.id", "104");
        request.setParameter("authorize.controller", "controller-updated");
        request.setParameter("authorize.action", "action-updated");
        request.setParameter("authorize.permission", "permission-updated");

        ActionProxy proxy = getActionProxy("/authorize/update");
        proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

    @Test
    public void testDestroy() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "101");

        ActionProxy proxy = getActionProxy("/authorize/destroy");
        proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

}

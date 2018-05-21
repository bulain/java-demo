package com.bulain.activiti.controller;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.activiti.model.Account;
import com.bulain.activiti.pojo.AccountView;
import com.bulain.common.dataset.DataSet;
import com.bulain.common.test.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

@DataSet(file = "test-data/init_accounts.xml")
public class AccountActionTest extends ActionTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testList() throws Exception {
        initServletMockObjects();
        request.setParameter("search.name", "name_page");
        request.setParameter("search.type", "type_page");

        ActionProxy proxy = getActionProxy("/account/list");
        AccountAction accountAction = (AccountAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        List<AccountView> listAccount = accountAction.getListAccount();
        assertEquals(3, listAccount.size());
    }

    @Test
    public void testNewn() throws Exception {
        initServletMockObjects();

        ActionProxy proxy = getActionProxy("/account/new");
        AccountAction accountAction = (AccountAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Account account = accountAction.getAccount();
        assertNotNull(account);
    }

    @Test
    public void testCreate() throws Exception {
        initServletMockObjects();
        request.setParameter("account.name", "name");
        request.setParameter("account.status", "status");
        request.setParameter("account.currency", "currency");
        request.setParameter("account.type", "type");

        ActionProxy proxy = getActionProxy("/account/create");
        proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

    @Test
    public void testShow() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "102");

        ActionProxy proxy = getActionProxy("/account/show");
        AccountAction accountAction = (AccountAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Account account = accountAction.getAccount();
        assertNotNull(account);
        assertEquals("name_102", account.getName());
        assertEquals("type_102", account.getType());
    }

    @Test
    public void testEdit() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "102");

        ActionProxy proxy = getActionProxy("/account/edit");
        AccountAction accountAction = (AccountAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Account account = accountAction.getAccount();
        assertNotNull(account);
        assertEquals("name_102", account.getName());
        assertEquals("type_102", account.getType());
    }

    @Test
    public void testUpdate() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "103");
        request.setParameter("account.name", "name_updated");
        request.setParameter("account.status", "status_updated");
        request.setParameter("account.currency", "currency_updated");
        request.setParameter("account.type", "type_updated");

        ActionProxy proxy = getActionProxy("/account/update");
        proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

    @Test
    public void testDestroy() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "101");

        ActionProxy proxy = getActionProxy("/account/destroy");
        proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

}

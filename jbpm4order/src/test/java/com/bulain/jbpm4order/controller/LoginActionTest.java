package com.bulain.jbpm4order.controller;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.model.Group;
import com.bulain.common.model.Login;
import com.bulain.common.page.Page;
import com.bulain.common.pojo.GroupSearch;
import com.bulain.common.pojo.LoginSearch;
import com.bulain.common.pojo.LoginView;
import com.bulain.common.service.GroupService;
import com.bulain.common.service.LoginService;
import com.bulain.common.test.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

@SeedDataSet(file = "data/init_action.xml")
public class LoginActionTest extends ActionTestCase {
    @Autowired
    private LoginService loginService;
    @Autowired
    private GroupService groupService;

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
    public void testCURD() throws Exception {
        initServletMockObjects();
        ActionProxy proxy = getActionProxy("/login/new");
        LoginAction loginAction = (LoginAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("login.loginName", "loginName");
        request.setParameter("login.email", "email@email.com");
        request.setParameter("login.hashedPassword", "hashedPassword");
        request.setParameter("login.enabled", "enabled");

        proxy = getActionProxy("/login/create");
        loginAction = (LoginAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        proxy = getActionProxy("/login/list");
        loginAction = (LoginAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        List<LoginView> listLogin = loginAction.getListLogin();
        assertEquals(2, listLogin.size());
        Page page = loginAction.getPage();
        assertEquals(1, page.getPage());

        Long id = listLogin.get(1).getId();

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/login/edit");
        loginAction = (LoginAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        Login login = loginAction.getLogin();
        assertEquals("loginName", login.getLoginName());
        assertEquals("email@email.com", login.getEmail());
        assertEquals("hashedPassword", login.getHashedPassword());
        assertEquals("enabled", login.getEnabled());

        initServletMockObjects();
        request.setParameter("login.id", Long.toString(id));
        request.setParameter("login.loginName", "loginName-updated");
        request.setParameter("login.email", "email-updated@email.com");
        request.setParameter("login.hashedPassword", "hashedPassword-updated");
        request.setParameter("login.enabled", "enabled-updated");
        proxy = getActionProxy("/login/update");
        loginAction = (LoginAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/login/show");
        loginAction = (LoginAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        login = loginAction.getLogin();
        assertEquals("loginName-updated", login.getLoginName());
        assertEquals("email-updated@email.com", login.getEmail());
        assertEquals("hashedPassword-updated", login.getHashedPassword());
        assertEquals("enabled-updated", login.getEnabled());

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/login/destroy");
        loginAction = (LoginAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

    @Test
    public void testGroup() throws Exception {
        Login login = new Login();
        login.setLoginName("loginName");
        login.setEmail("email@email.com");
        login.setHashedPassword("hashedPassword");
        login.setEnabled("enabled");
        loginService.insert(login);

        LoginSearch search = new LoginSearch();
        search.setLoginName(login.getLoginName());
        search.setEmail(login.getEmail());
        search.setEnabled(login.getEnabled());
        List<Login> find = loginService.find(search);
        assertTrue(find.size() > 0);
        Login login2 = find.get(find.size() - 1);
        Long loginId = login2.getId();

        Group group = new Group();
        group.setName("name");
        group.setNote("note");
        groupService.insert(group);

        GroupSearch gsearch = new GroupSearch();
        gsearch.setName(group.getName());
        List<Group> find2 = groupService.find(gsearch);
        assertTrue(find2.size() > 0);
        Group group2 = find2.get(find2.size() - 1);
        Long groupId = group2.getId();

        initServletMockObjects();
        request.setParameter("id", Long.toString(loginId));
        ActionProxy proxy = getActionProxy("/login/editGroup");
        LoginAction loginAction = (LoginAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        List<Group> listGroupDist = loginAction.getListGroupDist();
        assertEquals(0, listGroupDist.size());

        List<Group> listGroupSrc = loginAction.getListGroupSrc();
        assertEquals(2, listGroupSrc.size());

        initServletMockObjects();
        request.setParameter("id", Long.toString(loginId));
        request.setParameter("listGroupId", new String[]{Long.toString(groupId)});
        proxy = getActionProxy("/login/updateGroup");
        loginAction = (LoginAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("id", Long.toString(loginId));
        proxy = getActionProxy("/login/editGroup");
        loginAction = (LoginAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        listGroupDist = loginAction.getListGroupDist();
        assertEquals(1, listGroupDist.size());

        listGroupSrc = loginAction.getListGroupSrc();
        assertEquals(1, listGroupSrc.size());

        initServletMockObjects();
        request.setParameter("id", Long.toString(loginId));
        request.setParameter("listGroupId", new String[]{});
        proxy = getActionProxy("/login/updateGroup");
        loginAction = (LoginAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        loginService.delete(loginId);

        groupService.delete(groupId);
    }
}

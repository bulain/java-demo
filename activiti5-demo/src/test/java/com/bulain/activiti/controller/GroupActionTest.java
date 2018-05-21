package com.bulain.activiti.controller;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.activiti.model.Group;
import com.bulain.activiti.pojo.GroupView;
import com.bulain.common.dataset.DataSet;
import com.bulain.common.test.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

@DataSet(file = "test-data/init_groups.xml")
public class GroupActionTest extends ActionTestCase {

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

        ActionProxy proxy = getActionProxy("/group/list");
        GroupAction groupAction = (GroupAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        List<GroupView> listGroup = groupAction.getListGroup();
        assertEquals(3, listGroup.size());
    }

    @Test
    public void testNewn() throws Exception {
        initServletMockObjects();

        ActionProxy proxy = getActionProxy("/group/new");
        GroupAction groupAction = (GroupAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Group group = groupAction.getGroup();
        assertNotNull(group);
    }

    @Test
    public void testCreate() throws Exception {
        initServletMockObjects();
        request.setParameter("group.name", "name");
        request.setParameter("group.type", "type");

        ActionProxy proxy = getActionProxy("/group/create");
        proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

    @Test
    public void testShow() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "102");

        ActionProxy proxy = getActionProxy("/group/show");
        GroupAction groupAction = (GroupAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Group group = groupAction.getGroup();
        assertNotNull(group);
        assertEquals("name_102", group.getName());
        assertEquals("type_102", group.getType());
    }

    @Test
    public void testEdit() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "102");

        ActionProxy proxy = getActionProxy("/group/edit");
        GroupAction groupAction = (GroupAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Group group = groupAction.getGroup();
        assertNotNull(group);
        assertEquals("name_102", group.getName());
        assertEquals("type_102", group.getType());
    }

    @Test
    public void testUpdate() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "103");
        request.setParameter("group.name", "name_updated");
        request.setParameter("group.type", "type_updated");

        ActionProxy proxy = getActionProxy("/group/update");
        proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

    @Test
    public void testDestroy() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "101");

        ActionProxy proxy = getActionProxy("/group/destroy");
        proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

}

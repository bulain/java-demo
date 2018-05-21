package com.bulain.jbpm4order.controller;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.model.Order;
import com.bulain.common.page.Page;
import com.bulain.common.test.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

@SeedDataSet(file = "data/init_action.xml")
public class OrderActionTest extends ActionTestCase {
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
    public void testCRUD() throws Exception {
        initServletMockObjects();
        ActionProxy proxy = getActionProxy("/order/new");
        OrderAction orderAction = (OrderAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("order.name", "name");
        request.setParameter("order.note", "note");
        proxy = getActionProxy("/order/create");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        proxy = getActionProxy("/order/list");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        List<Order> listOrder = orderAction.getListOrder();
        assertEquals(1, listOrder.size());
        Page page = orderAction.getPage();
        assertEquals(1, page.getPage());

        Long id = listOrder.get(0).getId();

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/order/edit");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        Order order = orderAction.getOrder();
        assertEquals("name", order.getName());
        assertEquals("note", order.getNote());

        initServletMockObjects();
        request.setParameter("order.id", Long.toString(id));
        request.setParameter("order.name", "name-updated");
        request.setParameter("order.note", "note-updated");
        proxy = getActionProxy("/order/update");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/order/show");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        order = orderAction.getOrder();
        assertEquals("name-updated", order.getName());
        assertEquals("note-updated", order.getNote());

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/order/destroy");
        orderAction = (OrderAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }
}

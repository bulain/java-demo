package com.bulain.activiti.controller;

import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.activiti.model.Joda;
import com.bulain.activiti.pojo.JodaView;
import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

@DataSet(file = "test-data/init_jodas.xml")
public class JodaActionTest extends ActionTestCase {
    private Locale defaultLocale;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.CHINESE);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        Locale.setDefault(defaultLocale);
    }

    @Test
    public void testList() throws Exception {
        initServletMockObjects();
        request.setParameter("search.xdate", "2010-04-03");

        ActionProxy proxy = getActionProxy("/joda/list");
        JodaAction jodaAction = (JodaAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        List<JodaView> listJoda = jodaAction.getListJoda();
        assertEquals(3, listJoda.size());
        Page page = jodaAction.getPage();
        assertEquals(1, page.getPage());
    }

    @Test
    public void testNewn() throws Exception {
        initServletMockObjects();

        ActionProxy proxy = getActionProxy("/joda/new");
        JodaAction jodaAction = (JodaAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Joda joda = jodaAction.getJoda();
        assertNotNull(joda);
    }

    @Test
    public void testCreate() throws Exception {
        initServletMockObjects();
        request.setParameter("joda.xdate", "2010-04-03");
        request.setParameter("joda.xtime", "08:00");

        ActionProxy proxy = getActionProxy("/joda/create");
        proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

    @Test
    public void testShow() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "102");

        ActionProxy proxy = getActionProxy("/joda/show");
        JodaAction jodaAction = (JodaAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Joda joda = jodaAction.getJoda();
        assertNotNull(joda);
    }

    @Test
    public void testEdit() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "103");

        ActionProxy proxy = getActionProxy("/joda/edit");
        JodaAction jodaAction = (JodaAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Joda joda = jodaAction.getJoda();
        assertNotNull(joda);
    }

    @Test
    public void testUpdate() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "103");
        request.setParameter("joda.xdate", "2010-04-03");
        request.setParameter("joda.xtime", "08:00");

        ActionProxy proxy = getActionProxy("/joda/update");
        proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

    @Test
    public void testDestroy() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "101");

        ActionProxy proxy = getActionProxy("/joda/destroy");
        proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

}

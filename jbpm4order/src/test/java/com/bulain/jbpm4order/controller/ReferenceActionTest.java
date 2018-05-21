package com.bulain.jbpm4order.controller;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.model.Reference;
import com.bulain.common.page.Page;
import com.bulain.common.pojo.ReferenceView;
import com.bulain.common.test.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

@SeedDataSet(file = "data/init_action.xml")
public class ReferenceActionTest extends ActionTestCase {
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
        ActionProxy proxy = getActionProxy("/reference/new");
        ReferenceAction referenceAction = (ReferenceAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("referenceBean.name", "name");
        request.setParameter("referenceBean.code", "code");
        request.setParameter("referenceBean.textEN", "textEN");
        request.setParameter("referenceBean.textCN", "textCN");
        request.setParameter("referenceBean.catagory", "catagory");

        proxy = getActionProxy("/reference/create");
        referenceAction = (ReferenceAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        proxy = getActionProxy("/reference/list");
        referenceAction = (ReferenceAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        List<ReferenceView> listReference = referenceAction.getListReference();
        assertEquals(2, listReference.size());
        Page page = referenceAction.getPage();
        assertEquals(1, page.getPage());

        Long idEN = listReference.get(0).getId();
        Long idCN = listReference.get(1).getId();

        initServletMockObjects();
        request.setParameter("id", Long.toString(idEN));
        proxy = getActionProxy("/reference/edit");
        referenceAction = (ReferenceAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        Reference reference = referenceAction.getReference();
        assertEquals("name", reference.getName());
        assertEquals("code", reference.getCode());
        assertEquals("textEN", reference.getText());
        assertEquals("en", reference.getLang());
        assertEquals("catagory", reference.getCatagory());

        initServletMockObjects();
        request.setParameter("reference.id", Long.toString(idEN));
        request.setParameter("reference.name", "name-updated");
        request.setParameter("reference.code", "code-updated");
        request.setParameter("reference.text", "textEN-updated");
        request.setParameter("reference.lang", "en");
        request.setParameter("reference.catagory", "catagory-updated");
        proxy = getActionProxy("/reference/update");
        referenceAction = (ReferenceAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("id", Long.toString(idEN));
        proxy = getActionProxy("/reference/show");
        referenceAction = (ReferenceAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        reference = referenceAction.getReference();
        assertEquals("name-updated", reference.getName());
        assertEquals("code-updated", reference.getCode());
        assertEquals("textEN-updated", reference.getText());
        assertEquals("en", reference.getLang());
        assertEquals("catagory-updated", reference.getCatagory());

        initServletMockObjects();
        request.setParameter("id", Long.toString(idEN));
        proxy = getActionProxy("/reference/destroy");
        referenceAction = (ReferenceAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("id", Long.toString(idCN));
        proxy = getActionProxy("/reference/destroy");
        referenceAction = (ReferenceAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

}

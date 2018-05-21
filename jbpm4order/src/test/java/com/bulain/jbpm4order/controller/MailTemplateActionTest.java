package com.bulain.jbpm4order.controller;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.model.MailTemplate;
import com.bulain.common.page.Page;
import com.bulain.common.pojo.MailTemplateView;
import com.bulain.common.test.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

@SeedDataSet(file = "data/init_action.xml")
public class MailTemplateActionTest extends ActionTestCase {
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
        ActionProxy proxy = getActionProxy("/mailTemplate/new");
        MailTemplateAction mailTemplateAction = (MailTemplateAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("mailTemplate.name", "name");
        request.setParameter("mailTemplate.lang", "cn");
        request.setParameter("mailTemplate.subject", "subject");
        request.setParameter("mailTemplate.bodyName", "body");
        proxy = getActionProxy("/mailTemplate/create");
        mailTemplateAction = (MailTemplateAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        proxy = getActionProxy("/mailTemplate/list");
        mailTemplateAction = (MailTemplateAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        List<MailTemplateView> listMailTemplate = mailTemplateAction.getListMailTemplate();
        assertEquals(1, listMailTemplate.size());
        Page page = mailTemplateAction.getPage();
        assertEquals(1, page.getPage());

        Long id = listMailTemplate.get(0).getId();

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/mailTemplate/edit");
        mailTemplateAction = (MailTemplateAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        MailTemplate mailTemplate = mailTemplateAction.getMailTemplate();
        assertEquals("name", mailTemplate.getName());
        assertEquals("cn", mailTemplate.getLang());
        assertEquals("subject", mailTemplate.getSubject());
        assertEquals("body", mailTemplate.getBodyName());

        initServletMockObjects();
        request.setParameter("mailTemplate.id", Long.toString(id));
        request.setParameter("mailTemplate.name", "name-updated");
        request.setParameter("mailTemplate.lang", "cn");
        request.setParameter("mailTemplate.subject", "subject-updated");
        request.setParameter("mailTemplate.bodyName", "body-updated");
        proxy = getActionProxy("/mailTemplate/update");
        mailTemplateAction = (MailTemplateAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/mailTemplate/show");
        mailTemplateAction = (MailTemplateAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        mailTemplate = mailTemplateAction.getMailTemplate();
        assertEquals("name-updated", mailTemplate.getName());
        assertEquals("cn", mailTemplate.getLang());
        assertEquals("subject-updated", mailTemplate.getSubject());
        assertEquals("body-updated", mailTemplate.getBodyName());

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/mailTemplate/destroy");
        mailTemplateAction = (MailTemplateAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }
}

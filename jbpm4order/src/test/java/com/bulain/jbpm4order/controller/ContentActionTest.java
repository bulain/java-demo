package com.bulain.jbpm4order.controller;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.model.Content;
import com.bulain.common.page.Page;
import com.bulain.common.pojo.ContentView;
import com.bulain.common.test.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

@SeedDataSet(file = "data/init_action.xml")
public class ContentActionTest extends ActionTestCase {
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
        ActionProxy proxy = getActionProxy("/content/new");
        ContentAction contentAction = (ContentAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("content.fileName", "fileName");
        request.setParameter("content.contentType", "contentType");
        request.setParameter("content.catagory", "catagory");

        proxy = getActionProxy("/content/create");
        contentAction = (ContentAction) proxy.getAction();
        String pathname = "src/test/java/com/bulain/jbpm4order/controller/ContentActionTest.java";
        File blob = new File(pathname);
        contentAction.setBlob(blob);
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("search.fileName", "fileName");
        request.setParameter("search.contentType", "contentType");
        request.setParameter("search.catagory", "catagory");
        proxy = getActionProxy("/content/list");
        contentAction = (ContentAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        List<ContentView> listContent = contentAction.getListContent();
        assertEquals(1, listContent.size());
        Page page = contentAction.getPage();
        assertEquals(1, page.getPage());

        Long id = listContent.get(0).getId();

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/content/edit");
        contentAction = (ContentAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        Content content = contentAction.getContent();
        assertEquals("fileName", content.getFileName());
        assertEquals("contentType", content.getContentType());
        assertEquals("catagory", content.getCatagory());

        initServletMockObjects();
        request.setParameter("content.id", Long.toString(id));
        request.setParameter("content.fileName", "fileName-updated");
        request.setParameter("content.contentType", "contentType-updated");
        request.setParameter("content.catagory", "catagory-updated");
        proxy = getActionProxy("/content/update");
        contentAction = (ContentAction) proxy.getAction();
        contentAction.setBlob(blob);
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/content/show");
        contentAction = (ContentAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        content = contentAction.getContent();
        assertEquals("fileName-updated", content.getFileName());
        assertEquals("contentType-updated", content.getContentType());
        assertEquals("catagory-updated", content.getCatagory());

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/content/destroy");
        contentAction = (ContentAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }
}

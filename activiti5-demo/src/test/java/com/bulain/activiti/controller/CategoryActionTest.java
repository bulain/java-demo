package com.bulain.activiti.controller;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.activiti.model.Category;
import com.bulain.activiti.pojo.CategoryView;
import com.bulain.common.dataset.DataSet;
import com.bulain.common.test.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

@DataSet(file = "test-data/init_categorys.xml")
public class CategoryActionTest extends ActionTestCase {

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

        ActionProxy proxy = getActionProxy("/category/list");
        CategoryAction categoryAction = (CategoryAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        List<CategoryView> listCategory = categoryAction.getListCategory();
        assertEquals(3, listCategory.size());
    }

    @Test
    public void testNewn() throws Exception {
        initServletMockObjects();

        ActionProxy proxy = getActionProxy("/category/new");
        CategoryAction categoryAction = (CategoryAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Category category = categoryAction.getCategory();
        assertNotNull(category);
    }

    @Test
    public void testCreate() throws Exception {
        initServletMockObjects();
        request.setParameter("category.name", "name");
        request.setParameter("category.type", "type");

        ActionProxy proxy = getActionProxy("/category/create");
        proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

    @Test
    public void testShow() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "102");

        ActionProxy proxy = getActionProxy("/category/show");
        CategoryAction categoryAction = (CategoryAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Category category = categoryAction.getCategory();
        assertNotNull(category);
        assertEquals("name_102", category.getName());
        assertEquals("type_102", category.getType());
    }

    @Test
    public void testEdit() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "102");

        ActionProxy proxy = getActionProxy("/category/edit");
        CategoryAction categoryAction = (CategoryAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        Category category = categoryAction.getCategory();
        assertNotNull(category);
        assertEquals("name_102", category.getName());
        assertEquals("type_102", category.getType());
    }

    @Test
    public void testUpdate() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "103");
        request.setParameter("category.name", "name_updated");
        request.setParameter("category.type", "type_updated");

        ActionProxy proxy = getActionProxy("/category/update");
        proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

    @Test
    public void testDestroy() throws Exception {
        initServletMockObjects();
        request.setParameter("id", "101");

        ActionProxy proxy = getActionProxy("/category/destroy");
        proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

}

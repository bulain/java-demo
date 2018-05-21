package com.bulain.jbpm4order.controller;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.model.Person;
import com.bulain.common.page.Page;
import com.bulain.common.test.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

@SeedDataSet(file = "data/init_action.xml")
public class PersonActionTest extends ActionTestCase {
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
        ActionProxy proxy = getActionProxy("/person/new");
        PersonAction personAction = (PersonAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("person.firstName", "firstName");
        request.setParameter("person.lastName", "lastName");

        proxy = getActionProxy("/person/create");
        personAction = (PersonAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        proxy = getActionProxy("/person/list");
        personAction = (PersonAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        List<Person> listPerson = personAction.getListPerson();
        assertEquals(2, listPerson.size());
        Page page = personAction.getPage();
        assertEquals(1, page.getPage());

        Long id = listPerson.get(1).getId();

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/person/edit");
        personAction = (PersonAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        Person person = personAction.getPerson();
        assertEquals("firstName", person.getFirstName());
        assertEquals("lastName", person.getLastName());

        initServletMockObjects();
        request.setParameter("person.id", Long.toString(id));
        request.setParameter("person.firstName", "firstName-updated");
        request.setParameter("person.lastName", "lastName-updated");
        proxy = getActionProxy("/person/update");
        personAction = (PersonAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/person/show");
        personAction = (PersonAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        person = personAction.getPerson();
        assertEquals("firstName-updated", person.getFirstName());
        assertEquals("lastName-updated", person.getLastName());

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/person/destroy");
        personAction = (PersonAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

}

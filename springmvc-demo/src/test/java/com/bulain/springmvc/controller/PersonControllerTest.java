package com.bulain.springmvc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.model.Person;
import com.bulain.common.page.OrderBy;
import com.bulain.common.page.Page;
import com.bulain.common.pojo.PersonSearch;
import com.bulain.common.pojo.PersonView;
import com.bulain.common.test.ServiceTestCase;

@DataSet(file = "test-data/init_persons.xml")
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml",
        "classpath*:springmvc/applicationContext-jsp.xml"})
public class PersonControllerTest extends ServiceTestCase {
    @Autowired
    private PersonController personController;
    private Model model;
    private RedirectAttributes redirectAttributes;

    @Before
    public void setUp() {
        model = new BindingAwareModelMap();
        redirectAttributes = new RedirectAttributesModelMap();
    }

    @Test
    public void testList() {
        //prepare
        PersonSearch search = new PersonSearch();
        Page page = new Page();
        OrderBy orderBy = new OrderBy();

        HttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();

        //test
        String list = personController.list(search, page, orderBy, model, request, session);

        //assert
        assertEquals("person/list", list);
        Map<String, Object> asMap = model.asMap();
        assertTrue(asMap.get("search") instanceof PersonSearch);
        assertTrue(asMap.get("page") instanceof Page);
        assertTrue(asMap.get("orderBy") instanceof OrderBy);
        assertTrue(asMap.get("persons") instanceof List);
        page = (Page) asMap.get("page");
        assertEquals(1, page.getPage());
        @SuppressWarnings("unchecked")
        List<PersonView> persons = (List<PersonView>) asMap.get("persons");
        assertEquals(7, persons.size());
    }

    @Test
    public void testNewn() {
        String newn = personController.newn(model);

        assertEquals("person/new", newn);
        Map<String, Object> asMap = model.asMap();
        assertTrue(asMap.get("person") instanceof Person);
    }

    @Test
    public void testCreate() {
        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");

        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(person, "person");
        String create = personController.create(person, bindingResult, redirectAttributes);

        assertEquals("redirect:/person/list", create);
        Map<String, ?> flashAttributes = redirectAttributes.getFlashAttributes();
        assertEquals("create", flashAttributes.get("message"));
    }

    @Test
    public void testShow() {
        String show = personController.show(102L, model);

        assertEquals("person/show", show);
        Map<String, Object> asMap = model.asMap();
        assertTrue(asMap.get("person") instanceof Person);
    }

    @Test
    public void testEdit() {
        String edit = personController.edit(104L, model);

        assertEquals("person/edit", edit);
        Map<String, Object> asMap = model.asMap();
        assertTrue(asMap.get("person") instanceof Person);
    }

    @Test
    public void testDestroy() {
        String destroy = personController.destroy(101L, redirectAttributes);

        assertEquals("redirect:/person/list", destroy);
        Map<String, ?> flashAttributes = redirectAttributes.getFlashAttributes();
        assertEquals("destroy", flashAttributes.get("message"));
    }

}

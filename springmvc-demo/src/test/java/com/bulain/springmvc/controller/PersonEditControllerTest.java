package com.bulain.springmvc.controller;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.model.Person;
import com.bulain.common.test.ServiceTestCase;

@DataSet(file = "test-data/init_persons.xml")
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml",
        "classpath*:springmvc/applicationContext-jsp.xml"})
public class PersonEditControllerTest extends ServiceTestCase {
    @Autowired
    private PersonEditController personEditController;
    private RedirectAttributes redirectAttributes;

    @Before
    public void setUp() {
        redirectAttributes = new RedirectAttributesModelMap();
    }

    @Test
    public void testUpdate() {
        Person person = new Person();
        person.setId(Long.valueOf(104));
        person.setFirstName("firstName-updated");
        person.setLastName("lastName-updated");

        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(person, "person");
        String update = personEditController.update(person, bindingResult, redirectAttributes);

        assertEquals("redirect:/person/list", update);
        Map<String, ?> flashAttributes = redirectAttributes.getFlashAttributes();
        assertEquals("update", flashAttributes.get("message"));
    }

}

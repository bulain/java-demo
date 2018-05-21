package com.bulain.springmvc.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bulain.common.interceptor.ContextUtil;
import com.bulain.common.model.Person;
import com.bulain.common.service.PersonService;

@Controller
@RequestMapping(value = "/person/")
public class PersonEditController {
    private static final Logger logger = LoggerFactory.getLogger(PersonEditController.class);

    @Autowired
    private transient PersonService personService;

    @InitBinder
    public void initBinder(WebDataBinder b) {

    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "person/edit";
        }
        try {
            personService.update(person, true);
        } catch (Exception e) {
            logger.error("create()", e);

            String obj = ContextUtil.getMessage("person.model");
            String message = ContextUtil.getMessage("error.update", new Object[]{obj});
            redirectAttributes.addFlashAttribute("message", message);
            return "person/edit";
        }

        String obj = ContextUtil.getMessage("person.model");
        String message = ContextUtil.getMessage("info.update", new Object[]{obj});
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/person/list";
    }
    @ModelAttribute("person")
    public Person getPerson(@PathVariable("id") Long id) {
        return personService.get(id);
    }

}

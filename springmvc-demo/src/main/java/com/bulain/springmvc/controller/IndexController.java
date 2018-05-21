package com.bulain.springmvc.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bulain.common.controller.PageSupportActionSupport;
import com.bulain.common.model.Person;
import com.bulain.common.page.OrderBy;
import com.bulain.common.page.Page;
import com.bulain.common.pojo.PersonSearch;
import com.bulain.common.pojo.PersonView;
import com.bulain.common.service.PersonService;

@Controller
@RequestMapping(value = "/index/")
public class IndexController extends PageSupportActionSupport{
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private transient PersonService personService;

    @InitBinder
    public void initBinder(WebDataBinder b) {

    }

    @RequestMapping(value = {"", "list"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String list(PersonSearch search, Page page, OrderBy orderBy, Model model, HttpServletRequest request, HttpSession session) {
        if(RequestMethod.GET.name().equalsIgnoreCase(request.getMethod())){
            search = getSearchFromSession(PersonSearch.class, session);
            page = getPageFromSession(page, session);
            orderBy = getOrderByFromSession(orderBy, session);
        }else{
            page = new Page();
            orderBy = new OrderBy();
        }
        
        search.setOrderBy(orderBy.getOrderBy());
        search.setSequance(orderBy.getSequance());
        
        putBeanToSession(search, session);
        putBeanToSession(page, session);
        putBeanToSession(orderBy, session);
        
        List<Person> list = personService.page(search, page);
        List<PersonView> listView = formatList(list);
        
        model.addAttribute("search", search);
        model.addAttribute("orderBy", orderBy);
        model.addAttribute("page", page);
        model.addAttribute("persons", listView);
        
        Enumeration<String> headers = request.getHeaderNames();
        while(headers.hasMoreElements()){
            String name = headers.nextElement();
            String value = request.getHeader(name);
            System.out.printf("%s: %s\n", name, value);
        }
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        System.out.printf("%s: %s\n", "requestURI", requestURI);
        System.out.printf("%s: %s\n", "requestURL", requestURL.toString());
        
        String remoteAddr = request.getRemoteAddr();
        System.out.printf("%s: %s\n", "remoteAddr", remoteAddr);
        
        return "/index/list";
    }

    @RequestMapping(value = "new", method = {RequestMethod.GET, RequestMethod.POST})
    public String newn(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "/index/new";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid Person person, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/index/new";
        }
        try {
            personService.insert(person);
        } catch (Exception e) {
            logger.error("create()", e);
            redirectAttributes.addFlashAttribute("message", "create error");
            return "/index/new";
        }
        redirectAttributes.addFlashAttribute("message", "create");
        return "redirect:/index/list";
    }

    @RequestMapping(value = "show/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String show(@PathVariable("id") Long id, Model model) {
        Person person = personService.get(id);
        PersonView personView = formatItem(person);
        model.addAttribute("person", personView);
        return "/index/show";
    }

    @RequestMapping(value = "edit/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String edit(@PathVariable("id") Long id, Model model) {
        Person person = personService.get(id);
        model.addAttribute("person", person);
        return "/index/edit";
    }

    @RequestMapping(value = "destroy/{id}", method = RequestMethod.POST)
    public String destroy(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            personService.delete(id);
        } catch (Exception e) {
            logger.error("destroy()", e);
            redirectAttributes.addFlashAttribute("message", "destroy error");
            return "/index/list";
        }
        
        redirectAttributes.addFlashAttribute("message", "destroy");
        return "redirect:/index/list";
    }

    protected List<PersonView> formatList(List<Person> list) {
        List<PersonView> listView = new ArrayList<PersonView>();
        for (Person person : list) {
            listView.add(formatItem(person));
        }
        return listView;
    }

    protected PersonView formatItem(Person person) {
        PersonView personView = new PersonView(person);
        BeanUtils.copyProperties(person, personView);
        return personView;
    }

}
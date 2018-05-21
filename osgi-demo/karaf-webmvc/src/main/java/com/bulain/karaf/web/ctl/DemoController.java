package com.bulain.karaf.web.ctl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bulain.karaf.service.KarafService;
import com.bulain.karaf.web.pojo.Demo;

@Controller
@RequestMapping(value = "/demo/")
public class DemoController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    private KarafService karafService;

    @RequestMapping(value = "list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Demo list() {
        logger.debug("list()");
        
        String resp = karafService.sayHello("karaf-webmvc");

        Demo d = new Demo();
        d.setAbc(resp);
        return d;
    }

    @RequestMapping(value = "index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index() {
        logger.debug("index()");

        return "/demo/index";
    }

    public void setKarafService(KarafService karafService) {
        this.karafService = karafService;
    }
    
}

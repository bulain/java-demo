package com.bulain.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping(value = {"index"}, method = {RequestMethod.POST, RequestMethod.GET})
	public String index() {
		return "demo/index";
	}

}

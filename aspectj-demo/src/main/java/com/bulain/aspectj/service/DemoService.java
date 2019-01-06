package com.bulain.aspectj.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.bulain.aspectj.pojo.DemoReq;
import com.bulain.aspectj.pojo.DemoResp;

@Component
public class DemoService {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public DemoResp page(DemoReq req) {
		
		logger.info("{}", req);
		
		DemoResp resp = new DemoResp();
		BeanUtils.copyProperties(req, resp);;
		
		return resp;
	}
	
}

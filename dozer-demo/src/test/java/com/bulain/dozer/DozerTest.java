package com.bulain.dozer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Objects;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.dozer.pojo.DozerData;
import com.bulain.dozer.pojo.DozerInfo;
import com.bulain.dozer.pojo.OrderData;
import com.bulain.dozer.pojo.OrderInfo;

public class DozerTest {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testDozerXmlMapping() {
		DozerData src = new DozerData();
		src.setId(1L);
		src.setDate(new Date());
		src.setName("name");

		DozerBeanMapper mapper = new DozerBeanMapper();
		List<String> myMappingFiles = new ArrayList<String>();
		myMappingFiles.add("dozer/dozer-mapping.xml");
		mapper.setMappingFiles(myMappingFiles);
		DozerInfo dest = mapper.map(src, DozerInfo.class);

		logger.info("{}", src);
		logger.info("{}", dest);

		Objects.areEqual(src.getId(), dest.getId());
		Objects.areEqual(src.getName(), dest.getValue());
		Objects.areEqual(src.getDate(), dest.getDate());
		
	}
	
	@Test
	public void testDozerAnnotation() {
		OrderData src = new OrderData();
		src.setId(1L);
		src.setDate(new Date());
		src.setName("name");

		DozerBeanMapper mapper = new DozerBeanMapper();
		OrderInfo dest = mapper.map(src, OrderInfo.class);

		logger.info("{}", src);
		logger.info("{}", dest);
		
		Objects.areEqual(src.getId(), dest.getId());
		Objects.areEqual(src.getName(), dest.getValue());
		Objects.areEqual(src.getDate(), dest.getDate());

	}

}

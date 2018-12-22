package com.bulain.dozer;

import java.util.Date;

import org.assertj.core.util.Objects;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.bulain.dozer.pojo.DozerData;
import com.bulain.dozer.pojo.DozerInfo;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-dozer.xml"})
public class DozerSpringTest {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Mapper mapper;

	@Test
	public void testDozerSpringXml() {
		DozerData src = new DozerData();
		src.setId(1L);
		src.setDate(new Date());
		src.setName("name");

		DozerInfo dest = mapper.map(src, DozerInfo.class);

		logger.info("{}", src);
		logger.info("{}", dest);
		

		Objects.areEqual(src.getId(), dest.getId());
		Objects.areEqual(src.getName(), dest.getValue());
		Objects.areEqual(src.getDate(), dest.getDate());
		
	}
}

package com.bulain.dozer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

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
import com.bulain.dozer.pojo.OrderData;
import com.bulain.dozer.pojo.OrderInfo;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-dozer.xml"})
public class DozerSpringTest {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Mapper mapper;

	@Test
	public void testDozerXmlMapping() {
		DozerData src = new DozerData();
		src.setId(1L);
		src.setDate(new Date());
		src.setName("name");

		DozerInfo dest = mapper.map(src, DozerInfo.class);

		logger.info("{}", src);
		logger.info("{}", dest);

		assertThat(dest.getId()).isEqualTo(src.getId());
		assertThat(dest.getValue()).isEqualTo(src.getName());
		assertThat(dest.getDate()).isEqualTo(src.getDate());
		
	}
	
	@Test
	public void testDozerAnnotation() {
		OrderData src = new OrderData();
		src.setId(1L);
		src.setDate(new Date());
		src.setName("name");

		OrderInfo dest = mapper.map(src, OrderInfo.class);

		logger.info("{}", src);
		logger.info("{}", dest);
		
		assertThat(dest.getId()).isEqualTo(src.getId());
		assertThat(dest.getValue()).isEqualTo(src.getName());
		assertThat(dest.getDate()).isEqualTo(src.getDate());

	}
	
}

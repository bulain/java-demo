package com.bulain.vjkit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.vjkit.pojo.OrderData;
import com.bulain.vjkit.pojo.OrderInfo;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class OrikaTest {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testOrika() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(OrderData.class, OrderInfo.class)
			.field("name", "value").byDefault().register();

		MapperFacade mapper = mapperFactory.getMapperFacade();

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

package com.bulain.kafka;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.baidubce.services.tsdb.TsdbClient;
import com.baidubce.services.tsdb.model.Datapoint;

@Component
public class KafkaListen {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private TsdbClient tsdbClient;
	
	@KafkaListener(topics = "b38010722c4b432187e9b3826fb8a763__myTopic")
	public void listen(String content) {
		logger.debug("listen- {}", content);

		Datapoint datapoint = new Datapoint().withMetric("myTopic")
		        .withField("amount")
		        .addTag("A", "a")
		        .addStringValue(System.currentTimeMillis(), content);
		tsdbClient.writeDatapoints(Arrays.asList(new Datapoint[] { datapoint }));

	}

}

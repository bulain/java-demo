package com.bulain.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest(classes = KafkaApplication.class)
public class KafkaSender {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	
    @SuppressWarnings("static-access")
    @Test
	public void testSend() {
		for (int i = 0; i < 1000; i++) {
			try {
		        Thread.currentThread().sleep(100L);
	        } catch (InterruptedException e) {
	        }
			kafkaTemplate.send("b38010722c4b432187e9b3826fb8a763__myTopic", "this is a test message " + i);
		}
		
		try {
	        Thread.currentThread().sleep(100L);
        } catch (InterruptedException e) {
        }
		
	}

}

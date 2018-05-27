package com.bulain.iot;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = IotApplication.class)
public class IotPublish {

	@Value("${bce.iot.topic}")
	private String topic;
	@Autowired
	private MqttClient mqttClient;

	@SuppressWarnings("static-access")
	@Test
	public void testPulish() throws MqttException {
		for (int i = 0; i < 10; i++) {
			String content = "this is a demo message " + i;
			MqttMessage message = new MqttMessage(content.getBytes());
			mqttClient.publish(topic, message);
		}

		try {
			Thread.currentThread().sleep(1000L);
		} catch (InterruptedException e) {
		}
	}

}

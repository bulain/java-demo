package com.bulain.iot;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IotReceiver implements IMqttMessageListener {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void messageArrived(String topic, MqttMessage message) {
		logger.debug("MQTT message received: {}", message);
	}

}

package com.bulain.iot;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class IotInit implements DisposableBean {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${bce.iot.username}")
	private String username;
	@Value("${bce.iot.password}")
	private String password;
	@Value("${bce.iot.broker}")
	private String broker;
	@Value("${bce.iot.clientId}")
	private String clientId;
	@Value("${bce.iot.topic}")
	private String topic;

	private MqttClient mqttClient;

	@Bean
	public MqttClient mqttClient() {
		MqttConnectOptions connOpts = new MqttConnectOptions();
		connOpts.setUserName(username);
		connOpts.setPassword(password.toCharArray());

		try {
			mqttClient = new MqttClient(broker, clientId);
			mqttClient.connect(connOpts);
		} catch (MqttException e) {
			logger.error("mqttClient()-", e);
			throw new IllegalArgumentException(e);
		}

		try {
			mqttClient.subscribe(topic, new IotReceiver());
		} catch (MqttException e) {
			logger.error("mqttClient()-", e);
		}

		return mqttClient;
	}

	@Override
	public void destroy() throws Exception {
		if (mqttClient != null) {
			mqttClient.disconnect();
		}
	}

}

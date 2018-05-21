package com.bulain.jencks.jms;

public class TopicListener {
	public void onMessage(String body) {
		System.out.println("Topic: " + body);
	}
}

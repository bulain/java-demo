package com.bulain.jbpm4order.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopicListener {
    private static final Logger LOG = LoggerFactory.getLogger(P2pServer.class);
    
    public void onMessage(String body) {
        LOG.info("Topic: " + body);
    }
}

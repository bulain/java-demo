package com.bulain.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopicListener {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void onMessage(String body) {
        logger.info("Topic: " + body);
        
        boolean bool = false;
        if (bool) {
            throw new RuntimeException("topic");
        }
    }

}

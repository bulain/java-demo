package com.bulain.jbpm4order.workflow;

import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogListener implements EventListener {
    private static final long serialVersionUID = 6708821557647549910L;
    private static final Logger logger = LoggerFactory.getLogger(LogListener.class);

    private String msg;

    public void notify(EventListenerExecution execution) {
        logger.debug("notify(EventListenerExecution)-" + msg);
    }
}

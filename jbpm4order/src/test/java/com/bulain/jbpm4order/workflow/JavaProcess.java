package com.bulain.jbpm4order.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaProcess {
    private static final Logger logger = LoggerFactory.getLogger(JavaProcess.class);

    public String process(String str) {
        logger.debug("process(String)-" + str);
        return "to " + str;
    }
}

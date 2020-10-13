package com.bulain.common.log4j;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class Log4jThreadAppender extends AppenderSkeleton {
    protected void append(LoggingEvent event) {
        String log = getLayout().format(event);
        Log4jThreadContext.add(log);
    }

    public void close() {
    }

    public boolean requiresLayout() {
        return true;
    }

}

package com.bulain.common.log4j;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

public class Log4jDailyRollingFileAppender extends DailyRollingFileAppender {
    public Log4jDailyRollingFileAppender() {
        super();
        Log4jDailyRollingContext.setLog4jDailyRollingFileAppender(this);
    }

    public Log4jDailyRollingFileAppender(Layout layout, String filename, String datePattern) throws IOException {
        super(layout, filename, datePattern);
        Log4jDailyRollingContext.setLog4jDailyRollingFileAppender(this);
    }

    public synchronized void flush() {
        if (!checkEntryConditions()) {
            return;
        }
        List<LoggingEvent> list = Log4jDailyRollingContext.get();
        for (LoggingEvent event : list) {
            subAppend(event);
        }
        Log4jDailyRollingContext.clear();
    }

    @Override
    public void append(LoggingEvent event) {
        Log4jDailyRollingContext.add(event);
    }
}

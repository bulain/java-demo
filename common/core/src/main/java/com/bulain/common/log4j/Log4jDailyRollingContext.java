package com.bulain.common.log4j;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.spi.LoggingEvent;

public final class Log4jDailyRollingContext {
    private static Log4jDailyRollingFileAppender log4jDailyRollingFileAppender;
    private static ThreadLocal<List<LoggingEvent>> threadLocalData = new ThreadLocal<List<LoggingEvent>>();

    private Log4jDailyRollingContext() {

    }

    public static List<LoggingEvent> get() {
        List<LoggingEvent> list = threadLocalData.get();
        if (list == null) {
            list = new ArrayList<LoggingEvent>();
            threadLocalData.set(list);
        }
        return list;
    }

    public static void add(LoggingEvent log) {
        List<LoggingEvent> list = get();
        list.add(log);
    }

    public static void clear() {
        if (log4jDailyRollingFileAppender != null) {
            List<LoggingEvent> list = get();
            list.clear();
        }
    }

    public static void flush() {
        if (log4jDailyRollingFileAppender != null) {
            log4jDailyRollingFileAppender.flush();
        }
    }

    public static Log4jDailyRollingFileAppender getLog4jDailyRollingFileAppender() {
        return log4jDailyRollingFileAppender;
    }

    public static void setLog4jDailyRollingFileAppender(Log4jDailyRollingFileAppender log4jDailyRollingFileAppender) {
        Log4jDailyRollingContext.log4jDailyRollingFileAppender = log4jDailyRollingFileAppender;
    }
}

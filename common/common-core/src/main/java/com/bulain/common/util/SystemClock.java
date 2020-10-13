package com.bulain.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

public final class SystemClock {
    private SystemClock() {
    }

    public static long getTimeInMillis() {
        return DateTimeUtils.currentTimeMillis();
    }
    public static DateTime getDateTime() {
        return new DateTime(getTimeInMillis());
    }
    public static DateMidnight getDateMidnight() {
        return getDateTime().toDateMidnight();
    }
    public static LocalDate getLocalDate() {
        return getDateTime().toLocalDate();
    }
    public static LocalTime getLocalTime() {
        return getDateTime().toLocalTime();
    }
    public static LocalDateTime getLocalDateTime() {
        return getDateTime().toLocalDateTime();
    }
    public static Date getDate() {
        return getDateTime().toDate();
    }
    public static Calendar getCalendar() {
        return getDateTime().toCalendar(Locale.getDefault());
    }
}

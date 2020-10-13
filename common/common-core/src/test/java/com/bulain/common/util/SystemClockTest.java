package com.bulain.common.util;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SystemClockTest {
    private TimeZone defaultTimeZone;
    private Locale defaultLocale;
    private long currentMillis;

    @Before
    public void setUp() {
        // set time zone
        defaultTimeZone = TimeZone.getDefault();
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        DateTimeZone.setDefault(DateTimeZone.forID("UTC"));

        // set locale
        defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);

        // set time
        currentMillis = new DateTime(1982, 4, 3, 9, 30, 0).getMillis();
        DateTimeUtils.setCurrentMillisFixed(currentMillis);
    }

    @After
    public void tearDown() {
        TimeZone.setDefault(defaultTimeZone);
        DateTimeZone.setDefault(DateTimeZone.forTimeZone(defaultTimeZone));

        Locale.setDefault(defaultLocale);

        DateTimeUtils.setCurrentMillisSystem();
    }

    @Test
    public void testGetTimeInMillis() {
        assertEquals(currentMillis, SystemClock.getTimeInMillis());
    }

    @Test
    public void testGetDateTime() {
        assertEquals(new DateTime(currentMillis), SystemClock.getDateTime());
    }

    @Test
    public void testGetDateMidnight() {
        assertEquals(new DateMidnight(1982, 4, 3), SystemClock.getDateMidnight());
    }

    @Test
    public void testGetLocalDate() {
        assertEquals(new LocalDate(1982, 4, 3), SystemClock.getLocalDate());
    }

    @Test
    public void testGetLocalTime() {
        assertEquals(new LocalTime(9, 30, 0), SystemClock.getLocalTime());
    }

    @Test
    public void testGetLocalDateTime() {
        assertEquals(new LocalDateTime(1982, 4, 3, 9, 30, 0), SystemClock.getLocalDateTime());
    }

    @Test
    public void testGetDate() {
        assertEquals(new Date(currentMillis), SystemClock.getDate());
    }

    @Test
    public void testGetCalendar() {
        Calendar cal = Calendar.getInstance(DateTimeZone.forID("UTC").toTimeZone());
        cal.setTimeInMillis(currentMillis);
        assertEquals(cal, SystemClock.getCalendar());
    }

}

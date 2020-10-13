package com.bulain.common.converter;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;

public class DateTypeConverterTest {
    private Map<String, Object> context;
    private DateTypeConverter dateTypeConverter = new DateTypeConverter();
    
    @Before
    public void setUp(){
        context = new HashMap<String, Object>();
        context.put(ActionContext.LOCALE, Locale.CHINESE);
    }
    
    @Test
    public void testConvertFromStringMapStringArrayClass() {
        Object date = dateTypeConverter.convertFromString(context, new String[]{"2011-01-03 18:16"}, Date.class);
        DateTime dateTime = new DateTime(2011,1,3,18,16);
        assertEquals(dateTime.toDate(), date);
    }

    @Test
    public void testConvertToStringMapObject() {
        DateTime dateTime = new DateTime(2011,1,3,18,16);
        String convertToString = dateTypeConverter.convertToString(context, dateTime.toDate());
        assertEquals("2011-01-03", convertToString);
    }

}

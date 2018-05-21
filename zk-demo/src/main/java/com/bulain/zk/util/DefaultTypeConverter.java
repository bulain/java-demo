package com.bulain.zk.util;

import java.lang.reflect.Member;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

public class DefaultTypeConverter extends ognl.DefaultTypeConverter {
    @SuppressWarnings("rawtypes")
    public Object convertValue(Map context, Object target, Member member, String propertyName, Object value,
            Class toType) {
        if ((Date.class.equals(toType)) && (value instanceof String)) {
            String str = (String) value;
            Date date = null;
            try {
                date = DateUtils.parseDate(str, new String[]{"yyyy/MM/dd", "yyyy-MM-dd"});
            } catch (ParseException e) {
            }
            return date;
        }
        return convertValue(context, value, toType);
    }
}

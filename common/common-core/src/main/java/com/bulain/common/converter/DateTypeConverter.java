package com.bulain.common.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings({"rawtypes", "unchecked"})
public class DateTypeConverter extends StrutsTypeConverter {
    private Locale chinese = Locale.CHINESE;

    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
        Date date = null;

        Locale locale = getLocale(context);

        if (values != null && values.length > 0) {
            String sa = values[0];
            if (sa != null) {
                DateFormat[] dfs = getDateFormats(locale);
                for (DateFormat df : dfs) {
                    try {
                        date = df.parse(sa);
                        if (date != null) {
                            break;
                        }
                    } catch (ParseException ignore) {
                    }
                }
            }
        }

        return date;
    }

    private DateFormat[] getDateFormats(Locale locale) {
        DateFormat dt1, dt2, dt3;

        if (chinese.getLanguage().equals(locale.getLanguage())) {
            dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            dt3 = new SimpleDateFormat("yyyy-MM-dd");
        } else {
            dt1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            dt2 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            dt3 = new SimpleDateFormat("MM/dd/yyyy");
        }

        DateFormat dt4 = new SimpleDateFormat("HH:mm:ss");
        DateFormat dt5 = new SimpleDateFormat("HH:mm");
        DateFormat rfc3399 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        DateFormat[] dfs = {rfc3399, dt1, dt2, dt3, dt4, dt5};

        return dfs;
    }

    protected Locale getLocale(Map<String, Object> context) {
        if (context == null) {
            return Locale.getDefault();
        }

        Locale locale = (Locale) context.get(ActionContext.LOCALE);

        if (locale == null) {
            locale = Locale.getDefault();
        }

        return locale;
    }

    @Override
    public String convertToString(Map context, Object o) {
        String ds = null;

        Locale locale = getLocale(context);

        if (o instanceof Date) {
            Date date = (Date) o;
            SimpleDateFormat dt;
            if (chinese.getLanguage().equals(locale.getLanguage())) {
                dt = new SimpleDateFormat("yyyy-MM-dd");
            } else {
                dt = new SimpleDateFormat("MM/dd/yyyy");
            }

            ds = dt.format(date);
        }

        return ds;
    }

}

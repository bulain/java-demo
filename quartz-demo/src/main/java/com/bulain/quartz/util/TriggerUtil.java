package com.bulain.quartz.util;

import java.util.Date;

import org.joda.time.LocalDateTime;

import com.bulain.quartz.pojo.TaskTrigger;

public class TriggerUtil {

    public static final String FQ_ONCE = "once";
    public static final String FQ_MINUTE = "minute";
    public static final String FQ_HOURLY = "hourly";
    public static final String FQ_DAILY = "daily";
    public static final String FQ_WEEKLY = "weekly";
    public static final String FQ_MONTHLY = "monthly";

    public static final String OP_DAY = "day";
    public static final String OP_WEEK = "week";

    public static String cron(TaskTrigger trigger) {
        Date effDate = trigger.getEffDate();
        String frequency = trigger.getFrequency();
        int interval = trigger.getInterval();
        String option = trigger.getOption();
        String months = trigger.getMonths();
        String days = trigger.getDays();
        String weekth = trigger.getWeekth();
        String weeks = trigger.getWeeks();

        LocalDateTime ld = new LocalDateTime(effDate.getTime());
        int secondOfMinute = ld.getSecondOfMinute();
        int minuteOfHour = ld.getMinuteOfHour();
        int hourOfDay = ld.getHourOfDay();
        int dayOfMonth = ld.getDayOfMonth();
        int monthOfYear = ld.getMonthOfYear();
        int year = ld.getYear();

        String cron = null;
        if (FQ_ONCE.equals(frequency)) {//单次
            StringBuilder sb = new StringBuilder();
            sb.append(secondOfMinute);
            sb.append(" ");
            sb.append(minuteOfHour);
            sb.append(" ");
            sb.append(hourOfDay);
            sb.append(" ");
            sb.append(dayOfMonth);
            sb.append(" ");
            sb.append(monthOfYear);
            sb.append(" ");
            sb.append("?");
            sb.append(" ");
            sb.append(year);
            cron = sb.toString();
        } else if (FQ_MINUTE.equals(frequency)) {//每分
            StringBuilder sb = new StringBuilder();
            sb.append(secondOfMinute);
            sb.append(" ");
            sb.append("*");
            if (interval > 1) {
                sb.append("/");
                sb.append(interval);
            }
            sb.append(" ");
            sb.append("*");
            sb.append(" ");
            sb.append("*");
            sb.append(" ");
            sb.append("*");
            sb.append(" ");
            sb.append("?");
            cron = sb.toString();
        } else if (FQ_HOURLY.equals(frequency)) {//每时
            StringBuilder sb = new StringBuilder();
            sb.append(secondOfMinute);
            sb.append(" ");
            sb.append(minuteOfHour);
            sb.append(" ");
            sb.append("*");
            if (interval > 1) {
                sb.append("/");
                sb.append(interval);
            }
            sb.append(" ");
            sb.append("*");
            sb.append(" ");
            sb.append("*");
            sb.append(" ");
            sb.append("?");
            cron = sb.toString();
        } else if (FQ_DAILY.equals(frequency)) {//每天
            StringBuilder sb = new StringBuilder();
            sb.append(secondOfMinute);
            sb.append(" ");
            sb.append(minuteOfHour);
            sb.append(" ");
            sb.append(hourOfDay);
            sb.append(" ");
            sb.append("*");
            if (interval > 1) {
                sb.append("/");
                sb.append(interval);
            }
            sb.append(" ");
            sb.append("*");
            sb.append(" ");
            sb.append("?");
            cron = sb.toString();
        } else if (FQ_WEEKLY.equals(frequency)) {//每周
            StringBuilder sb = new StringBuilder();
            sb.append(secondOfMinute);
            sb.append(" ");
            sb.append(minuteOfHour);
            sb.append(" ");
            sb.append(hourOfDay);
            sb.append(" ");
            sb.append("?");
            sb.append(" ");
            sb.append("*");
            sb.append(" ");
            sb.append(weeks);
            if (interval > 1) {
                sb.append("/");
                sb.append(interval * 7);
            }
            cron = sb.toString();

        } else if (FQ_MONTHLY.equals(frequency)) {//每月
            StringBuilder sb = new StringBuilder();
            sb.append(secondOfMinute);
            sb.append(" ");
            sb.append(minuteOfHour);
            sb.append(" ");
            sb.append(hourOfDay);
            sb.append(" ");
            if (OP_DAY.equals(option)) {
                sb.append(days);
                sb.append(" ");
                if (months == null || months.trim().length() <= 0) {
                    sb.append("*");
                } else {
                    sb.append(months);
                }
                sb.append(" ");
                sb.append("?");
            } else if (OP_WEEK.equals(option)) {
                sb.append("?");
                sb.append(" ");
                if (months == null || months.trim().length() <= 0) {
                    sb.append("*");
                } else {
                    sb.append(months);
                }
                sb.append(" ");
                sb.append(weeks);
                sb.append("#");
                sb.append(weekth);
            }
            cron = sb.toString();
        }

        return cron;

    }
}

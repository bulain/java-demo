package com.bulain.quartz.util;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.quartz.CronScheduleBuilder;

import com.bulain.quartz.pojo.TaskTrigger;

public class TriggerUtilTest {

    @Test
    public void testQuartz() throws ParseException {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date effDate = df.parse("2017-10-09 14:25");
        TaskTrigger trigger = null;
        String cron = null;

        //单次
        trigger = new TaskTrigger();
        trigger.setEffDate(effDate);
        trigger.setFrequency(TriggerUtil.FQ_ONCE);
        cron = TriggerUtil.cron(trigger);
        assertEquals("0 25 14 9 10 ? 2017", cron);
        CronScheduleBuilder.cronSchedule(cron);

        //每分(1)
        trigger = new TaskTrigger();
        trigger.setEffDate(effDate);
        trigger.setFrequency(TriggerUtil.FQ_MINUTE);
        trigger.setInterval(1);
        cron = TriggerUtil.cron(trigger);
        assertEquals("0 * * * * ?", cron);
        CronScheduleBuilder.cronSchedule(cron);

        //每分(2)
        trigger = new TaskTrigger();
        trigger.setEffDate(effDate);
        trigger.setFrequency(TriggerUtil.FQ_MINUTE);
        trigger.setInterval(2);
        cron = TriggerUtil.cron(trigger);
        assertEquals("0 */2 * * * ?", cron);
        CronScheduleBuilder.cronSchedule(cron);

        //每时(1)
        trigger = new TaskTrigger();
        trigger.setEffDate(effDate);
        trigger.setFrequency(TriggerUtil.FQ_HOURLY);
        trigger.setInterval(1);
        cron = TriggerUtil.cron(trigger);
        assertEquals("0 25 * * * ?", cron);
        CronScheduleBuilder.cronSchedule(cron);

        //每时(2)
        trigger = new TaskTrigger();
        trigger.setEffDate(effDate);
        trigger.setFrequency(TriggerUtil.FQ_HOURLY);
        trigger.setInterval(2);
        cron = TriggerUtil.cron(trigger);
        assertEquals("0 25 */2 * * ?", cron);
        CronScheduleBuilder.cronSchedule(cron);

        //每天(1)
        trigger = new TaskTrigger();
        trigger.setEffDate(effDate);
        trigger.setFrequency(TriggerUtil.FQ_DAILY);
        trigger.setInterval(1);
        cron = TriggerUtil.cron(trigger);
        assertEquals("0 25 14 * * ?", cron);
        CronScheduleBuilder.cronSchedule(cron);

        //每天(2)
        trigger = new TaskTrigger();
        trigger.setEffDate(effDate);
        trigger.setFrequency(TriggerUtil.FQ_DAILY);
        trigger.setInterval(2);
        cron = TriggerUtil.cron(trigger);
        assertEquals("0 25 14 */2 * ?", cron);
        CronScheduleBuilder.cronSchedule(cron);

        //每周(1)
        trigger = new TaskTrigger();
        trigger.setEffDate(effDate);
        trigger.setFrequency(TriggerUtil.FQ_WEEKLY);
        trigger.setInterval(1);
        trigger.setWeeks("6");
        cron = TriggerUtil.cron(trigger);
        assertEquals("0 25 14 ? * 6", cron);
        CronScheduleBuilder.cronSchedule(cron);

        //每周(2)
        trigger = new TaskTrigger();
        trigger.setEffDate(effDate);
        trigger.setFrequency(TriggerUtil.FQ_WEEKLY);
        trigger.setInterval(2);
        trigger.setWeeks("3,6");
        cron = TriggerUtil.cron(trigger);
        assertEquals("0 25 14 ? * 3,6/14", cron);
        CronScheduleBuilder.cronSchedule(cron);

        //每月(日)(1)
        trigger = new TaskTrigger();
        trigger.setEffDate(effDate);
        trigger.setFrequency(TriggerUtil.FQ_MONTHLY);
        trigger.setOption(TriggerUtil.OP_DAY);
        trigger.setDays("9");
        cron = TriggerUtil.cron(trigger);
        assertEquals("0 25 14 9 * ?", cron);
        CronScheduleBuilder.cronSchedule(cron);

        //每月(日)(2)
        trigger = new TaskTrigger();
        trigger.setEffDate(effDate);
        trigger.setFrequency(TriggerUtil.FQ_MONTHLY);
        trigger.setMonths("1,4,7,10");
        trigger.setOption(TriggerUtil.OP_DAY);
        trigger.setDays("5,15,25");
        cron = TriggerUtil.cron(trigger);
        assertEquals("0 25 14 5,15,25 1,4,7,10 ?", cron);
        CronScheduleBuilder.cronSchedule(cron);

        //每月(周)(1)
        trigger = new TaskTrigger();
        trigger.setEffDate(effDate);
        trigger.setFrequency(TriggerUtil.FQ_MONTHLY);
        trigger.setOption(TriggerUtil.OP_WEEK);
        trigger.setWeeks("6");
        trigger.setWeekth("2");
        cron = TriggerUtil.cron(trigger);
        assertEquals("0 25 14 ? * 6#2", cron);
        CronScheduleBuilder.cronSchedule(cron);

        //每月(周)(2)
        trigger = new TaskTrigger();
        trigger.setEffDate(effDate);
        trigger.setFrequency(TriggerUtil.FQ_MONTHLY);
        trigger.setMonths("1,4,7,10");
        trigger.setOption(TriggerUtil.OP_WEEK);
        trigger.setWeeks("3,6");
        trigger.setWeekth("2");
        cron = TriggerUtil.cron(trigger);
        assertEquals("0 25 14 ? 1,4,7,10 3,6#2", cron);
        CronScheduleBuilder.cronSchedule(cron);

    }

}

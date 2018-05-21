package com.bulain.jbpm4order.integration.quartz;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.Ignore;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Ignore
public class TestJob implements Job {
    private static Logger LOG = LoggerFactory.getLogger(TestJob.class);

    public TestJob() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        Trigger trigger = context.getTrigger();
        String triggerName = trigger.getGroup() + "." + trigger.getName();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = df.format(context.getFireTime());
        Object jvalue = context.getMergedJobDataMap().get("jkey");
        Object tvalue = context.getMergedJobDataMap().get("tkey");
        LOG.info("run: " + jobDetail.getFullName() + " - " + triggerName + " @ " + format + " " + jvalue + " " + tvalue);
    }
}

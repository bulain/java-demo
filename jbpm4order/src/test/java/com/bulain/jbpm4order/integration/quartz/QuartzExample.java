package com.bulain.jbpm4order.integration.quartz;

import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzExample {

    public static void main(String[] args) throws Exception {
        QuartzExample example = new QuartzExample();
        example.run();
    }

    public void run() throws SchedulerException, ParseException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        sched.start();

        // schedule(sched, "job1", "group1", 3);
        // schedule(sched, "job2", "group2", 5);

        // unschedule(sched, "job1", "group1");
        // unschedule(sched, "job2", "group2");

        try {
            Thread.sleep(5L * 1000L);
        } catch (Exception e) {
        }

        sched.shutdown(true);
    }

    protected void schedule(Scheduler sched, String jobName, String groupName, int triggerCnt)
            throws SchedulerException, ParseException {
        JobDetail job = new JobDetail(jobName, groupName, TestJob.class);
        job.getJobDataMap().put("jkey", "jvalue");
        sched.addJob(job, true);

        for (int i = 0; i < triggerCnt; i++) {
            CronTrigger trigger = new CronTrigger("trigger" + (i + 1), groupName, "0/5 * * * * ?");
            trigger.setJobName(jobName);
            trigger.setJobGroup(groupName);
            trigger.getJobDataMap().put("tkey", "tvalue");
            sched.scheduleJob(trigger);
        }
    }

    protected void unschedule(Scheduler sched, String jobName, String groupName) throws SchedulerException {
        sched.deleteJob(jobName, groupName);
    }
}

package com.bulain.quartz.pojo;

import java.util.Date;

public class TaskTrigger {

    //配置
    private String active;//启用标志(是/否)
    private Date effDate;//开始时间
    private String expFlag;//到期标志
    private Date expDate;//到期时间
    
    //频率
    private String frequency;//频率(单次/每分/每时/每天/每周/每月)
    private int interval;//频率间隔
    private String months;//月列表(一月-十二月)(1-12)
    private String option;//选项(日/周)
    private String days;//日列表(1-31,最后一天)(1-31/L)
    private String weekth;//第几周(1-4,最后一周)(1-4/L)
    private String weeks;//周列表(周日-周六)(1-7)
    
    
    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }
    public Date getEffDate() {
        return effDate;
    }
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }
    public String getFrequency() {
        return frequency;
    }
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    public int getInterval() {
        return interval;
    }
    public void setInterval(int interval) {
        this.interval = interval;
    }
    public String getMonths() {
        return months;
    }
    public void setMonths(String months) {
        this.months = months;
    }
    public String getOption() {
        return option;
    }
    public void setOption(String option) {
        this.option = option;
    }
    public String getDays() {
        return days;
    }
    public void setDays(String days) {
        this.days = days;
    }
    public String getWeekth() {
        return weekth;
    }
    public void setWeekth(String weekth) {
        this.weekth = weekth;
    }
    public String getWeeks() {
        return weeks;
    }
    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }
    public String getExpFlag() {
        return expFlag;
    }
    public void setExpFlag(String expFlag) {
        this.expFlag = expFlag;
    }
    public Date getExpDate() {
        return expDate;
    }
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }
    
}

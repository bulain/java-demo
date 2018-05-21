package com.bulain.activiti.pojo;

import java.util.Date;

import com.bulain.common.page.Search;

public class JodaSearch extends Search {
    private static final long serialVersionUID = 6374323029613853764L;

    private Date xdate;
    private Date xtime;
    private Date xdatetimeFrom;
    private Date xdatetimeTo;

    public Date getXdate() {
        return xdate;
    }
    public void setXdate(Date xdate) {
        this.xdate = xdate;
    }
    public Date getXtime() {
        return xtime;
    }
    public void setXtime(Date xtime) {
        this.xtime = xtime;
    }
    public Date getXdatetimeFrom() {
        return xdatetimeFrom;
    }
    public void setXdatetimeFrom(Date xdatetimeFrom) {
        this.xdatetimeFrom = xdatetimeFrom;
    }
    public Date getXdatetimeTo() {
        return xdatetimeTo;
    }
    public void setXdatetimeTo(Date xdatetimeTo) {
        this.xdatetimeTo = xdatetimeTo;
    }
}

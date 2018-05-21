package com.bulain.activiti.pojo;

import java.util.Date;

import com.bulain.common.page.Search;

public class OrderSearch extends Search {
    private static final long serialVersionUID = 567310980322824574L;

    private String name;
    private Date effDateFrom;
    private Date effDateTo;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getEffDateFrom() {
        return effDateFrom;
    }
    public void setEffDateFrom(Date effDateFrom) {
        this.effDateFrom = effDateFrom;
    }
    public Date getEffDateTo() {
        return effDateTo;
    }
    public void setEffDateTo(Date effDateTo) {
        this.effDateTo = effDateTo;
    }

}

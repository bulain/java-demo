package com.bulain.activiti.model;

import java.io.Serializable;
import java.util.Date;

import com.bulain.common.model.Entity;
import com.bulain.common.model.Trackable;
import com.bulain.common.model.Versionable;

public class Joda implements Entity, Trackable, Versionable, Serializable {
    private static final long serialVersionUID = -5752868790524850684L;

    private Long id;
    private Date xdate;
    private Date xtime;
    private Date xdatetime;
    private Date xtimestamp;

    private String createdBy;
    private Date createdAt;
    private String updatedBy;
    private Date updatedAt;
    private Long version;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    public Date getXdatetime() {
        return xdatetime;
    }
    public void setXdatetime(Date xdatetime) {
        this.xdatetime = xdatetime;
    }
    public Date getXtimestamp() {
        return xtimestamp;
    }
    public void setXtimestamp(Date xtimestamp) {
        this.xtimestamp = xtimestamp;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public String getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Long getVersion() {
        return version;
    }
    public void setVersion(Long version) {
        this.version = version;
    }
}

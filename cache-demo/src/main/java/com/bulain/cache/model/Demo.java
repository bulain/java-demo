package com.bulain.cache.model;

import java.io.Serializable;

public class Demo implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String title;
    private String descr;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescr() {
        return descr;
    }
    public void setDescr(String descr) {
        this.descr = descr;
    }
    
}

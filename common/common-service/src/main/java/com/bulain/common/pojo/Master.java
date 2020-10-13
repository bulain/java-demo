package com.bulain.common.pojo;

import java.io.Serializable;

public class Master implements Serializable {
    private static final long serialVersionUID = 999925334992927320L;

    public static final Master DEFUALT_MASTER = new Master(null, "");

    private Long key;
    private String value;

    public Master() {
    }

    public Master(Long key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Master) {
            Master master = (Master) obj;
            return key == null ? master.getKey() == null : key.equals(master.getKey());
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return key + ":" + value;
    }

    public Long getKey() {
        return key;
    }
    public void setKey(Long key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}

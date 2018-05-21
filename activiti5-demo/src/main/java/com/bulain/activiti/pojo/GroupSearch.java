package com.bulain.activiti.pojo;

import com.bulain.common.page.Search;

public class GroupSearch extends Search {
    private static final long serialVersionUID = -8893872416604280253L;

    private String name;
    private String type;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}

package com.bulain.hibernate.pojo;

import com.bulain.common.page.Search;

public class GroupSearch extends Search {
    private static final long serialVersionUID = 8230817067414284935L;
    
    private String name;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
}

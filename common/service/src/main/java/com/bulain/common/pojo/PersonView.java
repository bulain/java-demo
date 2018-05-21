package com.bulain.common.pojo;

import com.bulain.common.model.Person;

public class PersonView extends Person{
    private static final long serialVersionUID = -8738030740293887084L;

    private String createdAtName;
    private String updatedAtName;
    
    public PersonView(Person p){
        setId(p.getId());
        setFirstName(p.getFirstName());
        setLastName(p.getLastName());
        setCreatedBy(p.getCreatedBy());
        setCreatedAt(p.getCreatedAt());
        setUpdatedBy(p.getUpdatedBy());
        setUpdatedAt(p.getUpdatedAt());
    }
    
    public String getCreatedAtName() {
        return createdAtName;
    }

    public void setCreatedAtName(String createdAtName) {
        this.createdAtName = createdAtName;
    }

    public String getUpdatedAtName() {
        return updatedAtName;
    }

    public void setUpdatedAtName(String updatedAtName) {
        this.updatedAtName = updatedAtName;
    }
    
}

package com.bulain.hibernate.pojo;

import com.bulain.common.page.Search;

public class UserSearch extends Search {
    private static final long serialVersionUID = -1933885749154159228L;
    
    private String firstName;
    private String lastName;
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}

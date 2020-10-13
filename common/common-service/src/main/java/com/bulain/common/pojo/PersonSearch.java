package com.bulain.common.pojo;

import com.bulain.common.page.Search;

public class PersonSearch extends Search {
    private static final long serialVersionUID = 3529309505585396737L;

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
    public String getFirstNameLike() {
        return "%" + firstName + "%";
    }
    public String getLastNameLike() {
        return "%" + lastName + "%";
    }
}

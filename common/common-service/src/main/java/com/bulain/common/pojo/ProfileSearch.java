package com.bulain.common.pojo;

import com.bulain.common.page.Search;

public class ProfileSearch extends Search {
    private static final long serialVersionUID = 1942246139418477787L;

    private Long personId;
    private String language;
    private String country;

    public Long getPersonId() {
        return personId;
    }
    public void setPersonId(Long personId) {
        this.personId = personId;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
package com.bulain.common.pojo;

import com.bulain.common.model.Profile;

public class ProfileView extends Profile {
    private static final long serialVersionUID = -7758341835008320563L;

    private String languageName;
    private String countryName;

    public ProfileView(final Profile model) {
        setId(model.getId());
        setPersonId(model.getPersonId());
        setLanguage(model.getLanguage());
        setCountry(model.getCountry());
        setCreatedBy(model.getCreatedBy());
        setCreatedAt(model.getCreatedAt());
        setUpdatedBy(model.getUpdatedBy());
        setUpdatedAt(model.getUpdatedAt());
    }

    public String getLanguageName() {
        return languageName;
    }
    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}

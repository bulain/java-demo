package com.bulain.common.pojo;

import com.bulain.common.page.Search;

public class ReferenceSearch extends Search {
    private static final long serialVersionUID = 6902889827403036175L;

    private String name;
    private String code;
    private String lang;
    private String catagory;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }
    public String getCatagory() {
        return catagory;
    }
    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }
}

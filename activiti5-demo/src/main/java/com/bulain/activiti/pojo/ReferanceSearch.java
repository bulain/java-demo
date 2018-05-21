package com.bulain.activiti.pojo;

import com.bulain.common.page.Search;

public class ReferanceSearch extends Search {
    private static final long serialVersionUID = 6902889827403036175L;

    private String name;
    private String code;
    private String lang;
    private String category;

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
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}

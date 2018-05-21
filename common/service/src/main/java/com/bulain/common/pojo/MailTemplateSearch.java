package com.bulain.common.pojo;

import com.bulain.common.page.Search;

public class MailTemplateSearch extends Search {
    private static final long serialVersionUID = 4795102464150194250L;

    private String name;
    private String lang;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }
}

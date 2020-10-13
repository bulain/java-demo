package com.bulain.common.pojo;

import com.bulain.common.page.Search;

public class AuthorizeSearch extends Search {
    private static final long serialVersionUID = -8515903306120274828L;

    private String controller;
    private String action;

    public String getController() {
        return controller;
    }
    public void setController(String controller) {
        this.controller = controller;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
}
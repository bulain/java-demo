package com.bulain.common.pojo;

import com.bulain.common.page.Search;

public class PermissionSearch extends Search {
    private static final long serialVersionUID = 2853930054339906649L;

    private String permission;

    public String getPermission() {
        return permission;
    }
    public void setPermission(String permission) {
        this.permission = permission;
    }
}

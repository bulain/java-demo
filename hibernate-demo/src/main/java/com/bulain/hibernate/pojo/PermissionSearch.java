package com.bulain.hibernate.pojo;

import com.bulain.common.page.Search;

public class PermissionSearch extends Search{
    private static final long serialVersionUID = 8916155391462400431L;
    
    private String permission;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    
}

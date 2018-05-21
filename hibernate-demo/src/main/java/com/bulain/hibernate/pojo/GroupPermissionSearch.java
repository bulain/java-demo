package com.bulain.hibernate.pojo;

import com.bulain.common.page.Search;

public class GroupPermissionSearch extends Search {
    private static final long serialVersionUID = 9222807955211526958L;
    
    private Long groupId;
    private String permission;
    
    public Long getGroupId() {
        return groupId;
    }
    
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    
    public String getPermission() {
        return permission;
    }
    
    public void setPermission(String permission) {
        this.permission = permission;
    }
    
}

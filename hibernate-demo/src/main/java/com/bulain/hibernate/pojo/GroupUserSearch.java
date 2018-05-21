package com.bulain.hibernate.pojo;

import com.bulain.common.page.Search;

public class GroupUserSearch extends Search{
    private static final long serialVersionUID = -2661404591404247491L;

    private Long userId;
    private Long groupId;
    
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getGroupId() {
        return groupId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    
}

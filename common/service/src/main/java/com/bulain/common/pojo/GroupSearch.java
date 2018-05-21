package com.bulain.common.pojo;

import java.util.List;

import com.bulain.common.page.Search;

public class GroupSearch extends Search {
    private static final long serialVersionUID = 8241714401190805669L;

    private String name;

    private Long loginId;
    private Long groupId;
    private List<Long> listLoginId;
    private List<String> listPermission;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Long> getListLoginId() {
        return listLoginId;
    }
    public void setListLoginId(List<Long> listLoginId) {
        this.listLoginId = listLoginId;
    }
    public Long getGroupId() {
        return groupId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    public List<String> getListPermission() {
        return listPermission;
    }
    public void setListPermission(List<String> listPermission) {
        this.listPermission = listPermission;
    }
    public Long getLoginId() {
        return loginId;
    }
    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

}

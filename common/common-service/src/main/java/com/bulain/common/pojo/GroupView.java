package com.bulain.common.pojo;

import java.util.List;

import com.bulain.common.model.Group;
import com.bulain.common.model.Permission;

public class GroupView extends Group {
    private static final long serialVersionUID = 2704625938044013497L;

    private String createdAtName;
    private String updatedAtName;

    private Long countGroupLogin;
    private List<Permission> listPermission;

    public GroupView(final Group model) {
        setId(model.getId());
        setName(model.getName());
        setNote(model.getNote());
        setCreatedBy(model.getCreatedBy());
        setCreatedAt(model.getCreatedAt());
        setUpdatedBy(model.getUpdatedBy());
        setUpdatedAt(model.getUpdatedAt());
    }

    public String getCreatedAtName() {
        return createdAtName;
    }
    public void setCreatedAtName(String createdAtName) {
        this.createdAtName = createdAtName;
    }
    public String getUpdatedAtName() {
        return updatedAtName;
    }
    public void setUpdatedAtName(String updatedAtName) {
        this.updatedAtName = updatedAtName;
    }
    public Long getCountGroupLogin() {
        return countGroupLogin;
    }
    public void setCountGroupLogin(Long countGroupLogin) {
        this.countGroupLogin = countGroupLogin;
    }

    public List<Permission> getListPermission() {
        return listPermission;
    }

    public void setListPermission(List<Permission> listPermission) {
        this.listPermission = listPermission;
    }

}

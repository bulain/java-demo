package com.bulain.common.pojo;

import java.util.List;

import com.bulain.common.model.Group;
import com.bulain.common.model.Login;

public class LoginView extends Login {
    private static final long serialVersionUID = -4796111767022620107L;

    private String enabledName;
    private String createdAtName;
    private String updatedAtName;

    private String personName;
    private List<Group> listGroup;

    public LoginView(final Login model) {
        setId(model.getId());
        setPersonId(model.getPersonId());
        setLoginName(model.getLoginName());
        setEmail(model.getEmail());
        setEnabled(model.getEnabled());
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
    public List<Group> getListGroup() {
        return listGroup;
    }
    public void setListGroup(List<Group> listGroup) {
        this.listGroup = listGroup;
    }
    public String getEnabledName() {
        return enabledName;
    }
    public void setEnabledName(String enabledName) {
        this.enabledName = enabledName;
    }
    public String getPersonName() {
        return personName;
    }
    public void setPersonName(String personName) {
        this.personName = personName;
    }
}

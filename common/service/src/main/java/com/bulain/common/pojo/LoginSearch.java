package com.bulain.common.pojo;

import java.util.List;

import com.bulain.common.page.Search;

public class LoginSearch extends Search {
    private static final long serialVersionUID = -8765991056341544073L;

    private Long personId;
    private Long groupId;
    private String loginName;
    private String email;
    private String enabled;

    private Long loginId;
    private List<Long> listGroupId;

    private List<String> listLoginName;

    public Long getPersonId() {
        return personId;
    }
    public void setPersonId(Long personId) {
        this.personId = personId;
    }
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEnabled() {
        return enabled;
    }
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
    public Long getGroupId() {
        return groupId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    public Long getLoginId() {
        return loginId;
    }
    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }
    public List<Long> getListGroupId() {
        return listGroupId;
    }
    public void setListGroupId(List<Long> listGroupId) {
        this.listGroupId = listGroupId;
    }
    public List<String> getListLoginName() {
        return listLoginName;
    }
    public void setListLoginName(List<String> listLoginName) {
        this.listLoginName = listLoginName;
    }
}

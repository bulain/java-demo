package com.bulain.common.model;

import java.io.Serializable;
import java.util.Date;

import com.bulain.common.model.Trackable;

public class Login implements Trackable, Serializable {
    private static final long serialVersionUID = 5088470747456872995L;

    private Long id;

    private Long personId;

    private String loginName;

    private String email;

    private String hashedPassword;

    private String enabled;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;

    public boolean equals(Object obj) {
        if (obj instanceof Login) {
            Login p = (Login) obj;
            Long pid = p.getId();
            return id == null ? pid == null : id.equals(pid);
        } else {
            return id == null;
        }
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword == null ? null : hashedPassword.trim();
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled == null ? null : enabled.trim();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
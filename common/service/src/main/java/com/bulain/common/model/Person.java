package com.bulain.common.model;

import java.io.Serializable;
import java.util.Date;

import com.bulain.common.model.Trackable;

public class Person implements Trackable, Serializable {
    private static final long serialVersionUID = -8633560005045157581L;

    private Long id;

    private String firstName;

    private String lastName;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;

    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person p = (Person) obj;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
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
package com.bulain.common.model;

import java.util.Date;

public interface Trackable {
    String getCreatedBy();
    void setCreatedBy(String createdBy);
    Date getCreatedAt();
    void setCreatedAt(Date createdAt);
    String getUpdatedBy();
    void setUpdatedBy(String updatedBy);
    Date getUpdatedAt();
    void setUpdatedAt(Date updatedAt);
}

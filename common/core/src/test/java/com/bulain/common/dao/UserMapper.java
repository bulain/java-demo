package com.bulain.common.dao;

import java.util.Map;

import com.bulain.common.model.User;

public interface UserMapper extends BasicMapper<User> {
    Map<Long, User> getMapUser();
    void setMapUser(Map<Long, User> mapUser);
    void clearMapUser();
}

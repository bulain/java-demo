package com.bulain.common.service;

import com.bulain.common.dao.BasicMapper;
import com.bulain.common.model.User;

public class UserServiceImpl extends BasicServiceImpl<User> implements UserService{
    private BasicMapper<User> userMapper;
    protected BasicMapper<User> getBasicMapper() {
        return userMapper;
    }
    public void setUserMapper(BasicMapper<User> userMapper) {
        this.userMapper = userMapper;
    }
}

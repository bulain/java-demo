package com.bulain.activiti.service;

import com.bulain.activiti.dao.UserMapper;
import com.bulain.activiti.model.User;
import com.bulain.activiti.pojo.UserSearch;
import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;

public class UserServiceImpl extends PagedServiceImpl<User, UserSearch> implements UserService {
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Override
    public PagedMapper<User, UserSearch> getPagedMapper() {
        return userMapper;
    }
}

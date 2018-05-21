package com.bulain.common.dao;

import java.util.List;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.model.Login;
import com.bulain.common.pojo.LoginSearch;

public interface LoginMapper extends PagedMapper<Login, LoginSearch> {
    Long countLoginByGroupId(Long groupId);
    List<Login> findLoginByGroupId(Long groupId);
    List<Login> findLoginByNoGroupId(Long groupId);
    List<Login> findLoginByLoginNames(String[] loginName);
}
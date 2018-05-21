package com.bulain.jbpm4order.dao;

import com.bulain.jbpm4order.model.GroupLogin;

public interface GroupLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupLogin record);

    int insertSelective(GroupLogin record);

    GroupLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupLogin record);

    int updateByPrimaryKey(GroupLogin record);
}
package com.bulain.jbpm4order.dao;

import com.bulain.jbpm4order.model.GroupPermission;

public interface GroupPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupPermission record);

    int insertSelective(GroupPermission record);

    GroupPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupPermission record);

    int updateByPrimaryKey(GroupPermission record);
}
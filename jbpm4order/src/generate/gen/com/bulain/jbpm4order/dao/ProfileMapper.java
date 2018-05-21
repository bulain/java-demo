package com.bulain.jbpm4order.dao;

import com.bulain.jbpm4order.model.Profile;

public interface ProfileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Profile record);

    int insertSelective(Profile record);

    Profile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Profile record);

    int updateByPrimaryKey(Profile record);
}
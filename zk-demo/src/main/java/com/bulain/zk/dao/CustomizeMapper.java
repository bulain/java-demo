package com.bulain.zk.dao;

import com.bulain.zk.model.Customize;

public interface CustomizeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Customize record);

    int insertSelective(Customize record);

    Customize selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Customize record);

    int updateByPrimaryKey(Customize record);
}
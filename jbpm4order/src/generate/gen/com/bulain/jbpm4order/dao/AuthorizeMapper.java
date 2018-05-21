package com.bulain.jbpm4order.dao;

import com.bulain.jbpm4order.model.Authorize;

public interface AuthorizeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Authorize record);

    int insertSelective(Authorize record);

    Authorize selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Authorize record);

    int updateByPrimaryKey(Authorize record);
}
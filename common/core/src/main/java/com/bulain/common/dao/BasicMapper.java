package com.bulain.common.dao;

public interface BasicMapper<T> {
    int deleteByPrimaryKey(Long id);
    int insert(T record);
    int insertSelective(T record);
    T selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(T record);
    int updateByPrimaryKey(T record);
    long countByDuplicate(T record);
}

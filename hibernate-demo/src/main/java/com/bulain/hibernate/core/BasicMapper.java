package com.bulain.hibernate.core;

public interface BasicMapper<T> {
    int deleteByPrimaryKey(Long id);
    int insert(T record);
    T selectByPrimaryKey(Long id);
    int updateByPrimaryKey(T record);
}

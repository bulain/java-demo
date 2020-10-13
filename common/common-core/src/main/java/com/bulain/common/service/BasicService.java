package com.bulain.common.service;

public interface BasicService<T> {
    T get(Long id);
    void insert(T record);
    void update(T record, boolean forced);
    void delete(Long id);
    long countByDuplicate(T record);
}

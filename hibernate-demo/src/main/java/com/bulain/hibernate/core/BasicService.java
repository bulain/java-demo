package com.bulain.hibernate.core;

import java.util.List;

public interface BasicService<T> {
    T get(Long id, List<String> properties);
    void insert(T record);
    void update(T record);
    void delete(Long id);
    long countByDuplicate(T record);
}

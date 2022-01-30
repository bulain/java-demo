package com.bulain.cache.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.bulain.cache.model.Demo;

public interface DemoService {

    @Cacheable(value = "DemoService", key = "#id")
    Demo get(Long id);

    @CacheEvict(value = "DemoService", key = "#data.id")
    Long insert(Demo data, boolean forced);

    @CacheEvict(value = "DemoService", key = "#data.id")
    Long update(Demo data, boolean forced);

    @CacheEvict(value = "DemoService", key = "#id")
    void delete(Long id);

    @CacheEvict(value = "DemoService", key = "#data.id")
    Long save(Demo data, boolean forced);

}

package com.bulain.common.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NoCacheServiceImpl implements CacheService {
    public Object get(Class<?> clz, Long id) {
        return null;
    }
    public Object get(Class<?> clz, String key) {
        return null;
    }

    public Map<String, Object> getByIds(Class<?> clz, Collection<Long> ids) {
        return new HashMap<String, Object>();
    }
    public Map<String, Object> getByKeys(Class<?> clz, Collection<String> keys) {
        return new HashMap<String, Object>();
    }

    public boolean set(Class<?> clz, Long id, Object obj) {
        return true;
    }
    public boolean set(Class<?> clz, Long id, Object obj, int expire) {
        return true;
    }
    public boolean set(Class<?> clz, String key, Object obj) {
        return true;
    }
    public boolean set(Class<?> clz, String key, Object obj, int expire) {
        return true;
    }

    public boolean add(Class<?> clz, Long id, Object obj) {
        return true;
    }
    public boolean add(Class<?> clz, Long id, Object obj, int expire) {
        return true;
    }
    public boolean add(Class<?> clz, String key, Object obj) {
        return true;
    }
    public boolean add(Class<?> clz, String key, Object obj, int expire) {
        return true;
    }

    public boolean replace(Class<?> clz, Long id, Object obj) {
        return true;
    }
    public boolean replace(Class<?> clz, Long id, Object obj, int expire) {
        return true;
    }
    public boolean replace(Class<?> clz, String key, Object obj) {
        return true;
    }
    public boolean replace(Class<?> clz, String key, Object obj, int expire) {
        return true;
    }

    public boolean delete(Class<?> clz, Long id) {
        return true;
    }
    public boolean delete(Class<?> clz, String key) {
        return true;
    }

    public void flushAll() {
    }
    public void shutdown() {
    }
}

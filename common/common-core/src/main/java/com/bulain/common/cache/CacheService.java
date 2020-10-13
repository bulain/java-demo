package com.bulain.common.cache;

import java.util.Collection;
import java.util.Map;

public interface CacheService {
    Object get(Class<?> clz, Long id);
    Object get(Class<?> clz, String key);

    Map<String, Object> getByIds(Class<?> clz, Collection<Long> ids);
    Map<String, Object> getByKeys(Class<?> clz, Collection<String> keys);

    boolean set(Class<?> clz, Long id, Object obj);
    boolean set(Class<?> clz, Long id, Object obj, int expire);
    boolean set(Class<?> clz, String key, Object obj);
    boolean set(Class<?> clz, String key, Object obj, int expire);

    boolean add(Class<?> clz, Long id, Object obj);
    boolean add(Class<?> clz, Long id, Object obj, int expire);
    boolean add(Class<?> clz, String key, Object obj);
    boolean add(Class<?> clz, String key, Object obj, int expire);

    boolean replace(Class<?> clz, Long id, Object obj);
    boolean replace(Class<?> clz, Long id, Object obj, int expire);
    boolean replace(Class<?> clz, String key, Object obj);
    boolean replace(Class<?> clz, String key, Object obj, int expire);

    boolean delete(Class<?> clz, Long id);
    boolean delete(Class<?> clz, String key);

    void flushAll();
    void shutdown();
}

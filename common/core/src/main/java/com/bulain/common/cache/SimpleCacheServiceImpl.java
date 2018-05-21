package com.bulain.common.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleCacheServiceImpl extends AbstractCacheServiceImpl {
    private Map<String, Object> map = new ConcurrentHashMap<String, Object>();

    public Object get(Class<?> clz, Long id) {
        return map.get(parseCacheKey(clz, id));
    }
    public Object get(Class<?> clz, String key) {
        return map.get(parseCacheKey(clz, key));
    }

    public Map<String, Object> getByIds(Class<?> clz, Collection<Long> ids) {
        Map<String, Object> temp = new HashMap<String, Object>();
        for (Long id : ids) {
            String cacheKey = parseCacheKey(clz, id);
            temp.put(cacheKey, map.get(cacheKey));
        }
        return temp;
    }
    public Map<String, Object> getByKeys(Class<?> clz, Collection<String> keys) {
        Map<String, Object> temp = new HashMap<String, Object>();
        for (String key : keys) {
            String cacheKey = parseCacheKey(clz, key);
            temp.put(cacheKey, map.get(cacheKey));
        }
        return null;
    }

    public boolean set(Class<?> clz, Long id, Object obj) {
        map.put(parseCacheKey(clz, id), obj);
        return true;
    }
    public boolean set(Class<?> clz, Long id, Object obj, int expire) {
        map.put(parseCacheKey(clz, id), obj);
        return true;
    }
    public boolean set(Class<?> clz, String key, Object obj) {
        map.put(parseCacheKey(clz, key), obj);
        return true;
    }
    public boolean set(Class<?> clz, String key, Object obj, int expire) {
        map.put(parseCacheKey(clz, key), obj);
        return true;
    }

    public boolean add(Class<?> clz, Long id, Object obj) {
        map.put(parseCacheKey(clz, id), obj);
        return true;
    }
    public boolean add(Class<?> clz, Long id, Object obj, int expire) {
        map.put(parseCacheKey(clz, id), obj);
        return true;
    }
    public boolean add(Class<?> clz, String key, Object obj) {
        map.put(parseCacheKey(clz, key), obj);
        return true;
    }
    public boolean add(Class<?> clz, String key, Object obj, int expire) {
        map.put(parseCacheKey(clz, key), obj);
        return true;
    }

    public boolean replace(Class<?> clz, Long id, Object obj) {
        map.put(parseCacheKey(clz, id), obj);
        return true;
    }
    public boolean replace(Class<?> clz, Long id, Object obj, int expire) {
        map.put(parseCacheKey(clz, id), obj);
        return true;
    }
    public boolean replace(Class<?> clz, String key, Object obj) {
        map.put(parseCacheKey(clz, key), obj);
        return true;
    }
    public boolean replace(Class<?> clz, String key, Object obj, int expire) {
        map.put(parseCacheKey(clz, key), obj);
        return true;
    }

    public boolean delete(Class<?> clz, Long id) {
        map.remove(parseCacheKey(clz, id));
        return true;
    }
    public boolean delete(Class<?> clz, String key) {
        map.remove(parseCacheKey(clz, key));
        return true;
    }

    public void flushAll() {
        map.clear();
    }
    public void shutdown() {
        map.clear();
    }
}

package com.bulain.cache.redis;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;

import redis.clients.jedis.JedisPool;

public class RedisCacheManager implements CacheManager, InitializingBean {

    private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();
    private JedisPool jedisPool;
    private int expireSeconds;

    private Cache createRedisCache(String name) {
        RedisCache cache = new RedisCache();
        cache.setJedisPool(jedisPool);
        cache.setName(name);
        cache.setExpireSeconds(expireSeconds);
        return cache;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(jedisPool, "jedisPool must not be null");
    }

    public Cache getCache(String name) {
        Cache cache = this.cacheMap.get(name);
        if (cache == null) {
            synchronized (this.cacheMap) {
                cache = this.cacheMap.get(name);
                if (cache == null) {
                    cache = createRedisCache(name);
                    this.cacheMap.put(name, cache);
                }
            }
        }
        return cache;
    }

    public Collection<String> getCacheNames() {
        return Collections.unmodifiableSet(this.cacheMap.keySet());
    }
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

}

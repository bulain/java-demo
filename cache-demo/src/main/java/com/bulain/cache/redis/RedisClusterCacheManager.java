package com.bulain.cache.redis;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;

import redis.clients.jedis.JedisCluster;

public class RedisClusterCacheManager implements CacheManager, InitializingBean {

    private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();
    private JedisCluster jedisCluster;
    private int expireSeconds;

    private Cache createRedisCache(String name) {
        RedisClusterCache cache = new RedisClusterCache();
        cache.setJedisCluster(jedisCluster);
        cache.setName(name);
        cache.setExpireSeconds(expireSeconds);
        return cache;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(jedisCluster, "jedisPool must not be null");
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
    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }
    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

}

package com.bulain.cache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import redis.clients.jedis.JedisCluster;

import com.bulain.cache.util.SerializeUtil;

import java.util.concurrent.Callable;

public class RedisClusterCache implements Cache {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JedisCluster jedisCluster;
    private String name;
    private int expireSeconds;

    private byte[] toBytes(Object obj) {
        byte[] ret = null;
        if (obj instanceof Number) {
            ret = obj.toString().getBytes();
        } else if (obj instanceof String str) {
            ret = str.getBytes();
        }
        return ret;
    }

    public void clear() {
        logger.trace("clear({})", name);

        jedisCluster.del(toBytes(name));
    }

    public void evict(Object key) {
        logger.trace("evict({},{})", name, key);

        if (key == null) {
            return;
        }

        jedisCluster.hdel(toBytes(name), toBytes(key));
    }

    public ValueWrapper get(Object key) {
        logger.trace("get({},{})", name, key);

        if (key == null) {
            return null;
        }

        byte[] value = null;
        value = jedisCluster.hget(toBytes(name), toBytes(key));
        return (value != null ? new SimpleValueWrapper(SerializeUtil.deserialize(value)) : null);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        ValueWrapper valueWrapper = get(key);
        if (valueWrapper == null) {
            return null;
        }
        return (T) valueWrapper.get();
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        ValueWrapper valueWrapper = get(key);
        if (valueWrapper == null) {
            return null;
        }
        return (T) valueWrapper.get();
    }

    public void put(Object key, Object value) {
        logger.trace("put({},{},{})", name, key, value);

        if (key == null || value == null) {
            return;
        }

        jedisCluster.hset(toBytes(name), toBytes(key), SerializeUtil.serialize(value));
        if (expireSeconds > 0) {
            jedisCluster.expire(toBytes(name), expireSeconds);
        }
    }

    public String getName() {
        return name;
    }

    public Object getNativeCache() {
        return jedisCluster;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

}

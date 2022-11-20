package com.bulain.cache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.bulain.cache.util.SerializeUtil;

import java.util.concurrent.Callable;

public class RedisCache implements Cache {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JedisPool jedisPool;
    private String name;
    private long expireSeconds;

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

        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(toBytes(name));
        } finally {
            jedis.close();
        }
    }

    public void evict(Object key) {
        logger.trace("evict({},{})", name, key);

        if (key == null) {
            return;
        }

        Jedis jedis = jedisPool.getResource();
        try {
            jedis.hdel(toBytes(name), toBytes(key));
        } finally {
            jedis.close();
        }
    }

    public ValueWrapper get(Object key) {
        logger.trace("get({},{})", name, key);

        if (key == null) {
            return null;
        }

        Jedis jedis = jedisPool.getResource();
        byte[] value = null;
        try {
            value = jedis.hget(toBytes(name), toBytes(key));
        } finally {
            jedis.close();
        }
        return (value != null ? new SimpleValueWrapper(SerializeUtil.deserialize(value)) : null);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        if (key == null) {
            return null;
        }
        ValueWrapper valueWrapper = get(key);
        if (valueWrapper == null) {
            return null;
        }
        return (T) valueWrapper.get();
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        if (key == null) {
            return null;
        }
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

        Jedis jedis = jedisPool.getResource();
        try {
            jedis.hset(toBytes(name), toBytes(key), SerializeUtil.serialize(value));
            if (expireSeconds > 0) {
                jedis.expire(toBytes(name), expireSeconds);
            }
        } finally {
            jedis.close();
        }
    }

    public String getName() {
        return name;
    }

    public Object getNativeCache() {
        return jedisPool;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

}

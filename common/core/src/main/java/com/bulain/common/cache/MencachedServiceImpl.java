package com.bulain.common.cache;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MencachedServiceImpl extends AbstractCacheServiceImpl {
    private static final Logger LOG = LoggerFactory.getLogger(MencachedServiceImpl.class);

    private static final int EXPIRE = 50;

    private MemcachedClient memcachedClient;
    private int expire;
    private long timeout;

    public MencachedServiceImpl() {
        super();
        this.expire = EXPIRE;
        this.timeout = MemcachedClient.DEFAULT_CONNECT_TIMEOUT;
    }

    public Object get(Class<?> clz, Long id) {
        try {
            return memcachedClient.get(parseCacheKey(clz, id), timeout);
        } catch (TimeoutException e) {
            LOG.error("get(Class<?>, Long)-", e);
        } catch (InterruptedException e) {
            LOG.error("get(Class, Long)-", e);
        } catch (MemcachedException e) {
            LOG.error("get(Class, Long)-", e);
        }
        return null;
    }
    public Object get(Class<?> clz, String key) {
        try {
            return memcachedClient.get(parseCacheKey(clz, key), timeout);
        } catch (TimeoutException e) {
            LOG.error("get(Class, String)-", e);
        } catch (InterruptedException e) {
            LOG.error("get(Class, String)-", e);
        } catch (MemcachedException e) {
            LOG.error("get(Class, String)-", e);
        }
        return null;
    }

    public Map<String, Object> getByIds(Class<?> clz, Collection<Long> ids) {
        try {
            return memcachedClient.get(parseCacheKeyById(clz, ids), timeout);
        } catch (TimeoutException e) {
            LOG.error("getByIds(Class, Collection<Long>)-", e);
        } catch (InterruptedException e) {
            LOG.error("getByIds(Class, Collection<Long>)-", e);
        } catch (MemcachedException e) {
            LOG.error("getByIds(Class, Collection<Long>)-", e);
        }
        return null;
    }
    public Map<String, Object> getByKeys(Class<?> clz, Collection<String> keys) {
        try {
            return memcachedClient.get(parseCacheKeyByKey(clz, keys), timeout);
        } catch (TimeoutException e) {
            LOG.error("getByIds(Class, Collection<String>)-", e);
        } catch (InterruptedException e) {
            LOG.error("getByIds(Class, Collection<String>)-", e);
        } catch (MemcachedException e) {
            LOG.error("getByIds(Class, Collection<String>)-", e);
        }
        return null;
    }

    public boolean set(Class<?> clz, Long id, Object obj) {
        try {
            return memcachedClient.set(parseCacheKey(clz, id), expire, obj, timeout);
        } catch (TimeoutException e) {
            LOG.error("set(Class, Long, Object)-", e);
        } catch (InterruptedException e) {
            LOG.error("set(Class, Long, Object)-", e);
        } catch (MemcachedException e) {
            LOG.error("set(Class, Long, Object)-", e);
        }
        return false;
    }
    public boolean set(Class<?> clz, Long id, Object obj, int expire) {
        try {
            return memcachedClient.set(parseCacheKey(clz, id), expire, obj, timeout);
        } catch (TimeoutException e) {
            LOG.error("set(Class, Long, Object, int)-", e);
        } catch (InterruptedException e) {
            LOG.error("set(Class, Long, Object, int)-", e);
        } catch (MemcachedException e) {
            LOG.error("set(Class, Long, Object, int)-", e);
        }
        return false;

    }
    public boolean set(Class<?> clz, String key, Object obj) {
        try {
            return memcachedClient.set(parseCacheKey(clz, key), expire, obj, timeout);
        } catch (TimeoutException e) {
            LOG.error("set(Class, String, Object)-", e);
        } catch (InterruptedException e) {
            LOG.error("set(Class, String, Object)-", e);
        } catch (MemcachedException e) {
            LOG.error("set(Class, String, Object)-", e);
        }
        return false;

    }
    public boolean set(Class<?> clz, String key, Object obj, int expire) {
        try {
            return memcachedClient.set(parseCacheKey(clz, key), expire, obj, timeout);
        } catch (TimeoutException e) {
            LOG.error("set(Class, String, Object, int)-", e);
        } catch (InterruptedException e) {
            LOG.error("set(Class, String, Object, int)-", e);
        } catch (MemcachedException e) {
            LOG.error("set(Class, String, Object, int)-", e);
        }
        return false;

    }

    public boolean add(Class<?> clz, Long id, Object obj) {
        try {
            return memcachedClient.add(parseCacheKey(clz, id), expire, obj, timeout);
        } catch (TimeoutException e) {
            LOG.error("add(Class, Long, Object)-", e);
        } catch (InterruptedException e) {
            LOG.error("add(Class, Long, Object)-", e);
        } catch (MemcachedException e) {
            LOG.error("add(Class, Long, Object)-", e);
        }
        return false;
    }
    public boolean add(Class<?> clz, Long id, Object obj, int expire) {
        try {
            return memcachedClient.add(parseCacheKey(clz, id), expire, obj, timeout);
        } catch (TimeoutException e) {
            LOG.error("add(Class, Long, Object, int)-", e);
        } catch (InterruptedException e) {
            LOG.error("add(Class, Long, Object, int)-", e);
        } catch (MemcachedException e) {
            LOG.error("add(Class, Long, Object, int)-", e);
        }
        return false;
    }
    public boolean add(Class<?> clz, String key, Object obj) {
        try {
            return memcachedClient.add(parseCacheKey(clz, key), expire, obj, timeout);
        } catch (TimeoutException e) {
            LOG.error("add(Class, String, Object)-", e);
        } catch (InterruptedException e) {
            LOG.error("add(Class, String, Object)-", e);
        } catch (MemcachedException e) {
            LOG.error("add(Class, String, Object)-", e);
        }
        return false;
    }
    public boolean add(Class<?> clz, String key, Object obj, int expire) {
        try {
            return memcachedClient.add(parseCacheKey(clz, key), expire, obj, timeout);
        } catch (TimeoutException e) {
            LOG.error("add(Class, String, Object, int)-", e);
        } catch (InterruptedException e) {
            LOG.error("add(Class, String, Object, int)-", e);
        } catch (MemcachedException e) {
            LOG.error("add(Class, String, Object, int)-", e);
        }
        return false;
    }

    public boolean replace(Class<?> clz, Long id, Object obj) {
        try {
            return memcachedClient.replace(parseCacheKey(clz, id), expire, obj, timeout);
        } catch (TimeoutException e) {
            LOG.error("replace(Class, Long, Object)-", e);
        } catch (InterruptedException e) {
            LOG.error("replace(Class, Long, Object)-", e);
        } catch (MemcachedException e) {
            LOG.error("replace(Class, Long, Object)-", e);
        }
        return false;
    }
    public boolean replace(Class<?> clz, Long id, Object obj, int expire) {
        try {
            return memcachedClient.replace(parseCacheKey(clz, id), expire, obj, expire);
        } catch (TimeoutException e) {
            LOG.error("replace(Class, Long, Object, int)-", e);
        } catch (InterruptedException e) {
            LOG.error("replace(Class, Long, Object, int)-", e);
        } catch (MemcachedException e) {
            LOG.error("replace(Class, Long, Object, int)-", e);
        }
        return false;
    }
    public boolean replace(Class<?> clz, String key, Object obj) {
        try {
            return memcachedClient.replace(parseCacheKey(clz, key), expire, obj, expire);
        } catch (TimeoutException e) {
            LOG.error("replace(Class, String, Object)-", e);
        } catch (InterruptedException e) {
            LOG.error("replace(Class, String, Object)-", e);
        } catch (MemcachedException e) {
            LOG.error("replace(Class, String, Object)-", e);
        }
        return false;
    }
    public boolean replace(Class<?> clz, String key, Object obj, int expire) {
        try {
            return memcachedClient.replace(parseCacheKey(clz, key), expire, obj, expire);
        } catch (TimeoutException e) {
            LOG.error("replace(Class, String, Object, int)-", e);
        } catch (InterruptedException e) {
            LOG.error("replace(Class, String, Object, int)-", e);
        } catch (MemcachedException e) {
            LOG.error("replace(Class, String, Object, int)-", e);
        }
        return false;
    }

    public boolean delete(Class<?> clz, Long id) {
        try {
            return memcachedClient.delete(parseCacheKey(clz, id));
        } catch (TimeoutException e) {
            LOG.error("delete(Class, Long)-", e);
        } catch (InterruptedException e) {
            LOG.error("delete(Class, Long)-", e);
        } catch (MemcachedException e) {
            LOG.error("delete(Class, Long)-", e);
        }
        return false;
    }
    public boolean delete(Class<?> clz, String key) {
        try {
            return memcachedClient.delete(parseCacheKey(clz, key));
        } catch (TimeoutException e) {
            LOG.error("delete(Class, String)-", e);
        } catch (InterruptedException e) {
            LOG.error("delete(Class, String)-", e);
        } catch (MemcachedException e) {
            LOG.error("delete(Class, String)-", e);
        }
        return false;
    }

    public void flushAll() {
        try {
            memcachedClient.flushAll();
        } catch (TimeoutException e) {
            LOG.error("flushAll()-", e);
        } catch (InterruptedException e) {
            LOG.error("flushAll()-", e);
        } catch (MemcachedException e) {
            LOG.error("flushAll()-", e);
        }
    }

    public void shutdown() {
        try {
            memcachedClient.shutdown();
        } catch (IOException e) {
            LOG.error("shutdown()-", e);
        }
    }

    public void setMemcachedClient(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }
    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
    public void setExpire(int expire) {
        this.expire = expire;
    }
}

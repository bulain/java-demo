package com.bulain.pool;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:spring/springContext-redis.xml"})
public class CollectionDemo {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void testLists() {
        Jedis jedis = jedisPool.getResource();

        String key = "lists";
        int size = 1000;
        
        jedis.del(key);
        for (int i = 0; i < size; i++) {
            jedis.rpush(key, Integer.toString(i));
        }

        Long llen = jedis.llen(key);
        assertEquals(Long.valueOf(size), llen);

        jedis.close();

    }
    
    @Test
    public void testSets() {
        Jedis jedis = jedisPool.getResource();

        String key = "sets";
        int size = 1000;
        for (int i = 0; i < size; i++) {
            jedis.sadd(key, Integer.toString(i));
        }

        Long llen = jedis.scard(key);
        assertEquals(Long.valueOf(size), llen);

        jedis.close();

    }

    
    @Test
    public void testZsets() {
        Jedis jedis = jedisPool.getResource();

        String key = "zsets";
        int size = 1000;
        for (int i = 0; i < size; i++) {
            jedis.zadd(key, i, Integer.toString(i));
        }

        Long llen = jedis.zcard(key);
        assertEquals(Long.valueOf(size), llen);

        jedis.close();

    }

}

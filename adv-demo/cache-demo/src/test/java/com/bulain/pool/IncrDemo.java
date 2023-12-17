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
public class IncrDemo {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void testIncr() throws InterruptedException {
        String key = "incr";

        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, "0");
        } finally {
            jedis.close();
        }

        for (int i = 0; i < 1000; i++) {
            jedis = jedisPool.getResource();
            try {
                Long incr = jedis.incr(key);
                assertEquals(Long.valueOf(i + 1), incr);
            } finally {
                jedis.close();
            }
        }
    }

    @Test
    public void testHincr() throws InterruptedException {
        String key = "hincr";
        String field = "incr";

        Jedis jedis = jedisPool.getResource();
        try {
            jedis.hset(key, field, "0");
        } finally {
            jedis.close();
        }

        for (int i = 0; i < 1000; i++) {
            jedis = jedisPool.getResource();
            try {
                Long incr = jedis.hincrBy(key, field, 1L);
                assertEquals(Long.valueOf(i + 1), incr);
            } finally {
                jedis.close();
            }
        }
    }

}

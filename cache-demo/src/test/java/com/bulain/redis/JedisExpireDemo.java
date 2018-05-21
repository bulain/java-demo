package com.bulain.redis;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisExpireDemo {

    @Test
    public void testExpire() throws InterruptedException {
        Jedis jedis = new Jedis("localhost");
        String key = "expire";
        jedis.set(key, "abc");
        jedis.expire(key, 5);

        String value = jedis.get(key);
        assertNotNull(value);
        Thread.sleep(6000);
        value = jedis.get(key);
        assertNull(value);

    }

    @Test
    public void testExpireAt() throws InterruptedException {
        Jedis jedis = new Jedis("localhost");
        String key = "expireAt";
        jedis.set(key, "abc");
        long unixTime = new Date().getTime() / 1000 + 5;
        jedis.expireAt(key, unixTime);

        String value = jedis.get(key);
        assertNotNull(value);
        Thread.sleep(6000);
        value = jedis.get(key);
        assertNull(value);
    }

    @Test
    public void testTtl() throws InterruptedException {
        Jedis jedis = new Jedis("localhost");
        String key = "ttl";
        jedis.set(key, "abc");
        jedis.expire(key, 5);
        Long ttl = jedis.ttl(key);
        assertEquals(Long.valueOf(5), ttl);
    }

}

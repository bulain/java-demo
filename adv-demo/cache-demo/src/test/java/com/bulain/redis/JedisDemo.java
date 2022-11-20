package com.bulain.redis;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JedisDemo {
    private static Jedis jedis;

    @BeforeAll
    public static void setUpClass() {
        jedis = new Jedis("localhost", 6379);
    }

    @AfterAll
    public static void tearDownClass() {
        jedis.disconnect();
    }

    @Test
    public void testGetSet() {
        //key
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        assertEquals("bar", value);

        jedis.del("foo");
        value = jedis.get("foo");
        assertEquals(null, value);

        //hash
        jedis.hset("key", "field", "hget");
        String hget = jedis.hget("key", "field");
        assertEquals("hget", hget);
        
        jedis.expire("key", 0L);
        jedis.del("key");
        hget = jedis.hget("key", "field");
        assertEquals(null, hget);
        
        
        
    }

    @Test
    public void testTransaction() {
        jedis.watch("watch01");

        Transaction tx = jedis.multi();
        tx.set("watch01", "v-1");
        List<Object> results = tx.exec();

        assertEquals(1, results.size());

    }

    @Test
    public void testPipeline() {
        Pipeline pipeline = jedis.pipelined();
        for (int i = 0; i < 10; i++) {
            pipeline.set("p" + i, "p" + i);
        }
        List<Object> results = pipeline.syncAndReturnAll();
        assertEquals(10, results.size());
    }

}

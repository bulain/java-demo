package com.bulain.redis;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class JedisDemo {
    private static Jedis jedis;

    @BeforeClass
    public static void setUpClass() {
        jedis = new Jedis("localhost");
    }

    @AfterClass
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
        
        jedis.expire("key", 0);
        jedis.del("key");
        hget = jedis.hget("key", "field");
        assertEquals(null, hget);
        
        
        
    }

    @Test
    public void testTransaction() {
        jedis.watch("watch01");

        Transaction tx = jedis.multi();
        tx.set("watch01", "v-1");
        List<Response<?>> results = tx.execGetResponse();

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

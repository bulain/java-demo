package com.bulain.memcached;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Properties;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SpyMemcachedDemo {
    private static MemcachedClient client;

    @BeforeAll
    public static void setUpClass() throws IOException {
        Properties systemProperties = System.getProperties();
        systemProperties.put("net.spy.log.LoggerImpl", "net.spy.memcached.compat.log.Log4JLogger");
        System.setProperties(systemProperties);

        client = new MemcachedClient(new BinaryConnectionFactory(), AddrUtil.getAddresses("localhost:11211"));
    }

    @AfterAll
    public static void tearDownClass() {
        client.shutdown();
    }

    @Test
    public void testGetSet() {
        client.set("someKey", 0, "SpyMemcached");
        Object value = client.get("someKey");
        assertEquals("SpyMemcached", value);
    }

    @Test
    public void testCas() {
        client.set("key", 0, "v1");
        CASValue<Object> c1 = client.gets("key");
        CASValue<Object> c2 = client.gets("key");

        CASResponse cas1 = client.cas("key", c1.getCas(), "v-1");
        assertEquals(CASResponse.OK, cas1);

        CASResponse cas2 = client.cas("key", c2.getCas(), "v-2");
        assertEquals(CASResponse.EXISTS, cas2);

        Object value = client.get("key");
        assertEquals("v-1", value);
    }
}

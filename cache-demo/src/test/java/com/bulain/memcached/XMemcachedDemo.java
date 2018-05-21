package com.bulain.memcached;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class XMemcachedDemo {
    private static MemcachedClient client;

    @BeforeClass
    public static void setUpClass() throws IOException {
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("localhost:11211"));
        client = builder.build();
    }

    @AfterClass
    public static void tearDown() throws IOException {
        client.shutdown();
    }

    @Test
    public void testGetSet() throws TimeoutException, InterruptedException, MemcachedException {
        client.set("hello", 0, "Hello, xmemcached");

        String value = client.get("hello");
        assertEquals("Hello, xmemcached", value);

        client.delete("hello");
        value = client.get("hello");
        assertEquals(null, value);
    }

    @Test
    public void testCas() throws TimeoutException, InterruptedException, MemcachedException {
        client.set("key", 0, "v1");

        GetsResponse<Object> c1 = client.gets("key");
        GetsResponse<Object> c2 = client.gets("key");

        boolean b1 = client.cas("key", 0, "v-1", c1.getCas());
        assertEquals(true, b1);

        boolean b2 = client.cas("key", 0, "v-2", c2.getCas());
        assertEquals(false, b2);

        Object value = client.get("key");
        assertEquals("v-1", value);
    }

}

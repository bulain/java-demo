package com.bulain.redis;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ConcurrentMap;

import org.junit.Test;
import org.redisson.Redisson;

public class RedissonDemo {
    @Test
    public void testRedisson() {
        Redisson redisson = Redisson.create();

        ConcurrentMap<String, String> map = redisson.getMap("anyMap");
        map.put("foo", "bar");
        String get = map.get("foo");
        assertEquals("bar", get);
        String putIfAbsent = map.putIfAbsent("foo", "bar2");
        assertEquals("bar", putIfAbsent);
        get = map.get("foo");
        assertEquals("bar", get);
        String remove = map.remove("foo");
        assertEquals("bar", remove);
        get = map.get("foo");
        assertEquals(null, get);

        redisson.shutdown();
    }
}

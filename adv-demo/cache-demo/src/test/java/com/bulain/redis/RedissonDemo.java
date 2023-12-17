package com.bulain.redis;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;

import java.util.concurrent.ConcurrentMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedissonDemo {
    @Test
    public void testRedisson() {
        RedissonClient redisson = Redisson.create();

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

package com.bulain.lettuce;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;

public class RedisSentinelDemo {

	RedisClient client = RedisClient.create("redis-sentinel://192.168.158.3:28381,192.168.158.4:28381/0#mymaster");

	@Test
	public void testSync() {
		RedisConnection<String, String> conn = client.connect();
		String set = conn.set("key", "value");
		String value = conn.get("key");

		assertEquals("OK", set);
		assertEquals("value", value);
	}

}

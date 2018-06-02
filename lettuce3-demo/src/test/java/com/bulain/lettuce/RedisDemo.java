package com.bulain.lettuce;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.lambdaworks.redis.RedisAsyncConnection;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;
import com.lambdaworks.redis.RedisFuture;

public class RedisDemo {

	RedisClient client = RedisClient.create("redis://192.168.158.3:6381");

	@Test
	public void testSync() {
		RedisConnection<String, String> conn = client.connect();
		String set = conn.set("key", "value");
		String value = conn.get("key");

		assertEquals("OK", set);
		assertEquals("value", value);
	}

	@Test
	public void testAsync() throws InterruptedException, ExecutionException {
		RedisAsyncConnection<String, String> conn = client.connectAsync();
		RedisFuture<String> set = conn.set("key", "value");
		RedisFuture<String> get = conn.get("key");

		assertEquals("OK", set.get());
		assertEquals("value", get.get());
	}

}

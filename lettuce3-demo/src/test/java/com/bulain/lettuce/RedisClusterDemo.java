package com.bulain.lettuce;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.lambdaworks.redis.RedisClusterAsyncConnection;
import com.lambdaworks.redis.RedisClusterConnection;
import com.lambdaworks.redis.RedisFuture;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.cluster.RedisClusterClient;

public class RedisClusterDemo {

	Iterable<RedisURI> redisURIs = Arrays
			.asList(new RedisURI[] {
					RedisURI.create("redis://192.168.158.3:7381"),
					RedisURI.create("redis://192.168.158.4:7381") });
	RedisClusterClient client = RedisClusterClient.create(redisURIs);

	@Test
	public void testSync() {
		RedisClusterConnection<String, String> conn = client.connectCluster();
		String set = conn.set("key", "value");
		String value = conn.get("key");

		assertEquals("OK", set);
		assertEquals("value", value);
	}

	@Test
	public void testAsync() throws InterruptedException, ExecutionException {
		RedisClusterAsyncConnection<String, String> conn = client
				.connectClusterAsync();
		RedisFuture<String> set = conn.set("key", "value");
		RedisFuture<String> get = conn.get("key");

		assertEquals("OK", set.get());
		assertEquals("value", get.get());
	}

}

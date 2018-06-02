package com.bulain.lettuce;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

import rx.Observable;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisFuture;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisStringAsyncCommands;
import com.lambdaworks.redis.api.rx.RedisStringReactiveCommands;
import com.lambdaworks.redis.api.sync.RedisStringCommands;

public class RedisSentinelDemo {

	RedisClient client = RedisClient.create("redis-sentinel://192.168.158.3:28381,192.168.158.4:28381/0#mymaster");

	@Test
	public void testSync() {
		StatefulRedisConnection<String, String> connection = client.connect();
		RedisStringCommands<String, String> sync = connection.sync();
		String set = sync.set("key", "value");
		String value = sync.get("key");

		assertEquals("OK", set);
		assertEquals("value", value);
	}

	@Test
	public void testAsync() throws InterruptedException, ExecutionException {
		StatefulRedisConnection<String, String> connection = client.connect();
		RedisStringAsyncCommands<String, String> async = connection.async();
		RedisFuture<String> set = async.set("key", "value");
		RedisFuture<String> get = async.get("key");

		assertEquals("OK", set.get());
		assertEquals("value", get.get());
	}

	@Test
	public void testReactive(){
		StatefulRedisConnection<String, String> connection = client.connect();
		RedisStringReactiveCommands<String, String> reactive = connection.reactive();
		Observable<String> set = reactive.set("key", "value");
		Observable<String> get = reactive.get("key");

		set.subscribe();

		String single = get.toBlocking().single();
		assertEquals("value",single);
		
	}
}

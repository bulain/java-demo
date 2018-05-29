package com.bulain.lettuce;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

import rx.Observable;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisFuture;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisStringAsyncCommands;
import com.lambdaworks.redis.api.rx.RedisStringReactiveCommands;
import com.lambdaworks.redis.api.sync.RedisStringCommands;

public class RedisDemo {

	RedisClient client = RedisClient.create("redis://localhost");

	@Test
	public void testSync() {
		StatefulRedisConnection<String, String> connection = client.connect();
		RedisStringCommands<String, String> sync = connection.sync();
		String set = sync.set("key", "value");
		String value = sync.get("key");

		System.out.println(set);
		System.out.println(value);
	}

	@Test
	public void testAsync() throws InterruptedException, ExecutionException {
		StatefulRedisConnection<String, String> connection = client.connect();
		RedisStringAsyncCommands<String, String> async = connection.async();
		RedisFuture<String> set = async.set("key", "value");
		RedisFuture<String> get = async.get("key");

		System.out.println(set.get());
		System.out.println(get.get());
	}

	@Test
	public void testReactive(){
		StatefulRedisConnection<String, String> connection = client.connect();
		RedisStringReactiveCommands<String, String> reactive = connection.reactive();
		Observable<String> set = reactive.set("key", "value");
		Observable<String> get = reactive.get("key");

		set.subscribe();

		String single = get.toBlocking().single();
		System.out.println(single);
		
	}
}

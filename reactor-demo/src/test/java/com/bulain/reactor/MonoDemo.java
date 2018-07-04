package com.bulain.reactor;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;

public class MonoDemo {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testMono() {
		
		//Deferred is the publisher, Promise the consumer
		MonoProcessor<String> promise = MonoProcessor.create();

		Mono<String> mono = promise.doOnSuccess(p -> logger.info("Success: {}", p))
		                .doOnTerminate(() -> logger.info("Terminate!"))
		                .doOnError(t -> logger.error(t.getMessage(), t));

		promise.onNext("Hello World!");
		//promise.onError(new IllegalArgumentException("Error! :P"));

		String s = mono.block();
		logger.info("s={}", s);
	}
	
}

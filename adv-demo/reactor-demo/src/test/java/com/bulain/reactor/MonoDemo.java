package com.bulain.reactor;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;

public class MonoDemo {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testMono() {
		Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
		Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
		Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);
	}

	@Test
	public void testMonoProcessor() {

		//Deferred is the publisher, Promise the consumer
		MonoProcessor<String> promise = MonoProcessor.create();

		Mono<String> mono = promise.doOnSuccess(p -> logger.info("Success: {}", p))
		                .doOnTerminate(() -> logger.info("Terminate!")).doOnError(t -> logger.error(t.getMessage(), t));

		promise.onNext("Hello World!");
		//promise.onError(new IllegalArgumentException("Error! :P"));

		String s = mono.block();
		logger.info("s={}", s);
	}

}

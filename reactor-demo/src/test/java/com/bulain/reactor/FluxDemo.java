package com.bulain.reactor;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Mono;

public class FluxDemo {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void simpleFlux() throws InterruptedException {
		// A Flux is a data publisher
		EmitterProcessor<String> stream = EmitterProcessor.<String> create();

		// Log values passing through the Flux and capture the first coming signal
		Mono<String> promise = stream.doOnNext(s -> logger.info("Consumed String {}", s)).next();

		// Publish a value
		stream.onNext("Hello World!");

		promise.block();
	}

	@Test
	public void transformValues() throws InterruptedException {
		// A Flux is a data publisher
		EmitterProcessor<String> stream = EmitterProcessor.<String> create();

		// Transform values passing through the Flux, observe and capture the result once.
		Mono<String> promise = stream.map(String::toUpperCase).doOnNext(s -> logger.info("UC String {}", s)).next();

		// Publish a value
		stream.onNext("Hello World!");

		promise.block();
	}

	@Test
	public void filterValues() throws InterruptedException {
		// A Flux is a data publisher
		EmitterProcessor<String> stream = EmitterProcessor.<String> create();

		// Filter values passing through the Flux, observe and capture the result once.
		Mono<List<String>> promise = stream.filter(s -> s.startsWith("Hello"))
		                .doOnNext(s -> logger.info("Filtered String {}", s)).collectList();

		// Publish a value
		stream.onNext("Hello World!");
		stream.onNext("Goodbye World!");
		stream.onComplete();

		promise.block();
	}
	
}

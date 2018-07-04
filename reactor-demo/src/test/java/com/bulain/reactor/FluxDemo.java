package com.bulain.reactor;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxDemo {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void testFlux() {
		Flux.just("Hello", "World").subscribe(System.out::println);
		Flux.fromArray(new Integer[]{1, 2, 3}).subscribe(System.out::println);
		Flux.empty().subscribe(System.out::println);
		Flux.range(1, 5).subscribe(System.out::println);
		Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
		Flux.interval(Duration.of(1000, ChronoUnit.MILLIS)).subscribe(System.out::println);
	}

	@Test
	public void testGenerate() {
		Flux.generate(sink -> {
			sink.next("Hello");
			sink.complete();
		}).subscribe(System.out::println);

		final Random random = new Random();
		Flux.generate(ArrayList::new, (list, sink) -> {
			int value = random.nextInt(100);
			list.add(value);
			sink.next(value);
			if (list.size() == 5) {
				sink.complete();
			}
			return list;
		}).subscribe(System.out::println);
	}

	@Test
	public void testCreate() {
		Flux.create(sink -> {
			for (int i = 0; i < 5; i++) {
				sink.next(i);
			}
			sink.complete();
		}).subscribe(System.out::println);
	}

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

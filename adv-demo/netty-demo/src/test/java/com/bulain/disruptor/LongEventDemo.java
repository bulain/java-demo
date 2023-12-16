package com.bulain.disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;


import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unchecked")
public class LongEventDemo {

    @Test
    public void testProducer() {
        // Executor that will be used to construct new threads for consumers
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        // The factory for the event
        LongEventFactory factory = new LongEventFactory();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 16;

        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, threadFactory);

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler()).then(new ClearingEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; l < 128L; l++) {
            bb.putLong(0, l);
            producer.onData(bb);
        }

        disruptor.shutdown();
    }

    @Test
    public void testProducerWithTranslator() {
        // Executor that will be used to construct new threads for consumers
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        // The factory for the event
        LongEventFactory factory = new LongEventFactory();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 16;

        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, threadFactory);

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler()).then(new ClearingEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; l < 128L; l++) {
            bb.putLong(0, l);
            producer.onData(bb);
        }

        disruptor.shutdown();
    }

    @Test
    public void testMultiThread() throws InterruptedException {
        // Executor that will be used to construct new threads for consumers
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        // The factory for the event
        LongEventFactory factory = new LongEventFactory();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 16;

        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, threadFactory);

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler()).then(new ClearingEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        final LongEventProducer producer = new LongEventProducer(ringBuffer);
        
        int nThreads = 5;
        long size = 128L;
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        for (long l = 0; l < size; l++) {
            final ByteBuffer bb = ByteBuffer.allocate(8);
            bb.putLong(0, l);
            Callable<Void> task = new Callable<Void>() {
                public Void call() throws Exception {
                    producer.onData(bb);
                    return null;
                }
            };
            executorService.submit(task);
        }

        executorService.shutdown();
        executorService.awaitTermination(60L, TimeUnit.SECONDS);
        disruptor.shutdown();

    }

}

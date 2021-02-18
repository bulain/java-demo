package com.bulain.disruptor;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongEventProducer {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb) {
        long sequence = ringBuffer.next(); // Grab the next sequence
        try {
            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor for the sequence
            event.set(bb.getLong(0)); // Fill with data

            logger.debug("set:{}", event.get());
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}

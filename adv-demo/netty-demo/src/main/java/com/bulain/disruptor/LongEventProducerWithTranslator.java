package com.bulain.disruptor;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongEventProducerWithTranslator {
    private static final Logger logger = LoggerFactory.getLogger(LongEventProducerWithTranslator.class);
    
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
        @Override
        public void translateTo(LongEvent event, long sequence, ByteBuffer bb) {
            event.set(bb.getLong(0));
            
            logger.debug("set:{}", event.get());
        }
    };

    public void onData(ByteBuffer bb) {
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }
}

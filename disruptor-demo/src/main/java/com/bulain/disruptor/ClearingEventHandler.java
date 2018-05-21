package com.bulain.disruptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmax.disruptor.EventHandler;

public class ClearingEventHandler implements EventHandler<LongEvent> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        long l = event.get();
        event.set(0L);
        logger.debug("LongEvent:{}->{}", l, event.get());
    }

}

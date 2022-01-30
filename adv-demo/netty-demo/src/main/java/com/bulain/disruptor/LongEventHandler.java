package com.bulain.disruptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        logger.debug("LongEvent:{}", event.get());
    }

}
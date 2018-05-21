package com.bulain.camel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EdiProducer {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    public InputStream action(){
        InputStream is = null;
        try {
            is = new FileInputStream("src/test/resources/test-data/invoic.edi");
        } catch (FileNotFoundException e) {
            logger.error("action()-", e);
        }
        return is;
    }
    
}

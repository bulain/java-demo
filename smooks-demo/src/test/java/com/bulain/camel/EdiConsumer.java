package com.bulain.camel;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.milyn.Smooks;
import org.milyn.smooks.edi.unedifact.UNEdifactReaderConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EdiConsumer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public InputStream action(InputStream is) {

        StringWriter writer = new StringWriter();
        try {
            Smooks smooks = new Smooks();
            smooks.setReaderConfig(new UNEdifactReaderConfigurator("urn:org.milyn.edi.unedifact:d93a-mapping:*"));

            smooks.filterSource(new StreamSource(is), new StreamResult(writer));
        } catch (Exception e) {
            logger.error("action()-", e);
        }

        InputStream ret = new ByteArrayInputStream(writer.toString().getBytes());
        
        return ret;
    }

}

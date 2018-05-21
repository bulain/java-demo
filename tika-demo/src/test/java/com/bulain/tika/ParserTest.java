package com.bulain.tika;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class ParserTest {
    @Test
    public void testAutoDetectParser() throws IOException, SAXException, TikaException {
        Parser parser = new AutoDetectParser();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("com/bulain/tika/ParserTest.class");
        InputStream ins = resource.openStream();

        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        ContentHandler handler = new BodyContentHandler();
        parser.parse(ins, handler, metadata, context);

        System.out.println(metadata);
        System.out.println(handler);
    }
}

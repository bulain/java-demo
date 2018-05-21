package com.bulain.smooks.csv;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.milyn.Smooks;
import org.milyn.container.ExecutionContext;
import org.xml.sax.SAXException;

public class Csv2XmlDemo {
    
    @Test
    public void testSmooks() throws IOException, SAXException {
        InputStream is = new FileInputStream("src/test/resources/test-data/smooks.csv");
        Smooks smooks = new Smooks("src/test/resources/smooks/csv2xml.xml");

        try {
            ExecutionContext executionContext = smooks.createExecutionContext();
            StringWriter writer = new StringWriter();
            smooks.filterSource(executionContext, new StreamSource(is), new StreamResult(writer));
            
            String xml = writer.toString();
            System.out.println(xml);
            
        } finally {
            smooks.close();
        }
    }
}

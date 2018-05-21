package com.bulain.smooks.csv;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.milyn.Smooks;
import org.milyn.container.ExecutionContext;
import org.milyn.payload.JavaResult;
import org.xml.sax.SAXException;

public class Csv2JavaDemo {

    @SuppressWarnings("unchecked")
    @Test
    public void testSmooks() throws IOException, SAXException {
        InputStream is = new FileInputStream("src/test/resources/test-data/smooks.csv");
        Smooks smooks = new Smooks("src/test/resources/smooks/csv2java.xml");

        try {
            ExecutionContext executionContext = smooks.createExecutionContext();
            JavaResult result = new JavaResult();
            smooks.filterSource(executionContext, new StreamSource(is), result);
            
            List<Customer> list = (List<Customer>) result.getBean("customerList");
            System.out.println(list);
        } finally {
            smooks.close();
        }
    }

}

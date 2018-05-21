package com.bulain.xml;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.bulain.jibx.pojo.Message;
import com.bulain.jibx.pojo.Request;
import com.bulain.jibx.pojo.Response;

public class JibxTest {

    @Test
    public void testMarshaller() throws JiBXException, IOException {
        IBindingFactory factory = BindingDirectory.getFactory(Request.class);
        IMarshallingContext mctx = factory.createMarshallingContext();

        Message message = new Message();

        Request request = new Request();
        request.setFirstName("request.firstName");
        request.setLastName("request.lastName");

        Response response = new Response();
        response.setFirstName("response.firstName");
        response.setLastName("response.lastName");

        message.setRequest(request);
        message.setResponse(response);

        //mctx.setIndent(2);
        OutputStream out = new ByteArrayOutputStream();
        mctx.setOutput(out, null);
        mctx.marshalDocument(message);

        File testFile = getTestFile();
        String xml = FileUtils.readFileToString(testFile);

        assertEquals(xml, out.toString());
    }

    @Test
    public void testUnmarshall() throws JiBXException, IOException {
        IBindingFactory factory = BindingDirectory.getFactory(Request.class);
        IUnmarshallingContext unmarshallingContext = factory.createUnmarshallingContext();

        FileInputStream in = new FileInputStream(getTestFile());
        Message message = (Message) unmarshallingContext.unmarshalDocument(in, null);

        assertNotNull(message);

        Request request = message.getRequest();
        assertNotNull(request);
        assertEquals("request.firstName", request.getFirstName());
        assertEquals("request.lastName", request.getLastName());

        Response response = message.getResponse();
        assertNotNull(response);
        assertEquals("response.firstName", response.getFirstName());
        assertEquals("response.lastName", response.getLastName());

    }

    private File getTestFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("com/bulain/xml/message.xml");
        return resource.getFile();
    }

}

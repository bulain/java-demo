package com.bulain.cxf.jetty.mime;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.bulain.cxf.mime.MimeService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath:spring/applicationContext-mimeclient.xml"})
public class SpringMimeClient {
    @Autowired
    private MimeService mimeService;

    @Test
    public void upload() throws IOException {
        String filename = "auth.properties";
        InputStream is = new FileInputStream("src/test/resources/auth.properties");
        MultivaluedMap<String, String> headers = new MultivaluedHashMap<String, String>();
        Attachment file = new Attachment(is, headers);
        Response resp = mimeService.upload(filename, file);
        assertEquals(200, resp.getStatus());
    }

    @Test
    public void download() throws IOException {
        String id = "1";
        Response resp = mimeService.download(id);
        Object entity = resp.getEntity();
        assertEquals(200, resp.getStatus());

        if (entity instanceof InputStream) {
            InputStream is = (InputStream) entity;
            String str = IOUtils.toString(is);
            System.out.println(str);
        }

    }

}

package com.bulain.cxf.jetty.mime;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.cxf.helpers.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpMimiClient {
    HttpClient httpClient = HttpClientBuilder.create().build();

    @Test
    public void upload() throws ClientProtocolException, IOException {
        postUpload("http://localhost:8083/jaxrs/mime/upload");
    }

    @Test
    public void multiUpload() throws ClientProtocolException, IOException {
        postUpload("http://localhost:8083/jaxrs/mime/multiUpload");
    }

    private void postUpload(String url) throws IOException, ClientProtocolException {
        File file = new File("src/test/resources/auth.properties");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .addBinaryBody("file", file, ContentType.DEFAULT_BINARY, "auth.properties")
                .addTextBody("id", "abc", ContentType.DEFAULT_BINARY);

        HttpEntity entity = builder.build();
        HttpPost req = new HttpPost(url);

        req.setEntity(entity);
        HttpResponse resp = httpClient.execute(req);
        assertEquals(200, resp.getStatusLine().getStatusCode());

        HttpEntity respEntity = resp.getEntity();
        String text = EntityUtils.toString(respEntity, "UTF-8");

        System.out.println(text);
    }

    @Test
    public void download() throws ClientProtocolException, IOException {
        HttpGet req = new HttpGet("http://localhost:8083/jaxrs/mime/download/1");
        HttpResponse resp = httpClient.execute(req);
        assertEquals(200, resp.getStatusLine().getStatusCode());
        
        HttpEntity entity = resp.getEntity();
        Header contentType = entity.getContentType();
        InputStream is = entity.getContent();
        String str = IOUtils.toString(is);
        
        System.out.println(contentType);
        System.out.println(str);
        
    }

}

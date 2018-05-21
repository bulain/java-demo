package com.bulain.httpclient;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

public class SoapDemo {

    private CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    @Test
    public void testPost0251() throws Exception {

        String body = load("test-data/0251.xml");
        String url = "";
        String resp = executePost(url, body);
        System.out.println(resp);

    }

    private String load(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return IOUtils.toString(resource.getInputStream(), "UTF-8");
    }

    private String executePost(String url, String body) throws Exception {
        String username = "bc_wms";
        String password = "wms123456";
        String str = username + ":" + password;
        String authorization = "Basic " + Base64.encodeBase64String(str.getBytes("UTF-8"));

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/xml");
        httpPost.addHeader("Authorization", authorization);

        StringEntity entity = new StringEntity(body, "UTF-8");
        httpPost.setEntity(entity);
        HttpResponse resp = httpClient.execute(httpPost);
        HttpEntity respEntity = resp.getEntity();
        return EntityUtils.toString(respEntity, "UTF-8");
    }

}

package com.bulain.cxf.jetty.mime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.cxf.helpers.IOUtils;
import org.junit.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RestMimeClient {
    private RestTemplate template = new RestTemplate();

    @Test
    @SuppressWarnings("rawtypes")
    public void upload() throws FileNotFoundException {
        String url = "http://localhost:8083/jaxrs/mime/upload";
        FileSystemResource resource = new FileSystemResource(new File("src/test/resources/auth.properties"));
        MultiValueMap<String, Object> req = new LinkedMultiValueMap<String, Object>();
        req.add("file", resource);
        req.add("id", "abc");
        ResponseEntity<HashMap> resp = template.postForEntity(url, req, HashMap.class);

        System.out.println(resp);
    }

    @Test
    public void downloadAsByte() {
        String url = "http://localhost:8083/jaxrs/mime/download/1";
        ResponseEntity<byte[]> resp = template.getForEntity(url, byte[].class);
        HttpHeaders headers = resp.getHeaders();
        MediaType contentType = headers.getContentType();
        byte[] body = resp.getBody();

        System.out.println(contentType);
        System.out.println(new String(body));
    }

    @Test
    public void downloadAsStream() throws IOException {
        String url = "http://localhost:8083/jaxrs/mime/download/1";
        ResponseEntity<ByteArrayResource> resp = template.getForEntity(url, ByteArrayResource.class);
        HttpHeaders headers = resp.getHeaders();
        MediaType contentType = headers.getContentType();
        ByteArrayResource body = resp.getBody();

        System.out.println(contentType);
        System.out.println(IOUtils.toString(body.getInputStream()));
    }

}

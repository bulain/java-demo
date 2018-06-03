package com.bulain.cxf.jetty.jaxrs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

import com.bulain.cxf.jaxrs.Person;

public class JaxrsClient {

    //commons httpclient
    @Test
    public void testListXml() throws IOException {
        URL url = new URL("http://localhost:8083/jaxrs/persons");
        InputStream in = url.openStream();
        System.out.println(IOUtils.toString(in));
    }

    @Test
    public void testGetXml() throws IOException {
        URL url = new URL("http://localhost:8083/jaxrs/persons/10");
        InputStream in = url.openStream();
        System.out.println(IOUtils.toString(in));
    }

    @Test
    public void testCreateXml() throws HttpException, IOException {
        PostMethod post = new PostMethod("http://localhost:8083/jaxrs/persons");
        String content = "<person><firstName>firstName</firstName><lastName>lastName</lastName></person>";
        String contentType = "text/xml";
        String charset = "UTF-8";
        RequestEntity entity = new StringRequestEntity(content, contentType, charset);
        post.setRequestEntity(entity);
        HttpClient httpclient = new HttpClient();

        try {
            int code = httpclient.executeMethod(post);
            assertEquals(204, code);
        } finally {
            post.releaseConnection();
        }
    }

    @Test
    public void testUpdateXml() throws HttpException, IOException {
        PutMethod put = new PutMethod("http://localhost:8083/jaxrs/persons/10");
        String content = "<person><firstName>firstName</firstName><id>10</id><lastName>lastName</lastName></person>";
        String contentType = "text/xml";
        String charset = "UTF-8";
        RequestEntity entity = new StringRequestEntity(content, contentType, charset);
        put.setRequestEntity(entity);
        HttpClient httpclient = new HttpClient();

        try {
            int code = httpclient.executeMethod(put);
            assertEquals(204, code);
        } finally {
            put.releaseConnection();
        }
    }

    @Test
    public void testDeleteXml() throws HttpException, IOException {
        DeleteMethod delete = new DeleteMethod("http://localhost:8083/jaxrs/persons/11");
        HttpClient httpclient = new HttpClient();

        try {
            int code = httpclient.executeMethod(delete);
            assertEquals(204, code);
        } finally {
            delete.releaseConnection();
        }
    }

    // WebClient json
    @Test
    public void testListJson() {
        WebClient client = WebClient.create("http://localhost:8083/jaxrs");
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON_TYPE);
        client.path("persons");
        Collection<? extends Person> persons = client.getCollection(Person.class);
        System.out.println(persons);
    }

    @Test
    public void testGetJson() {
        WebClient client = WebClient.create("http://localhost:8083/jaxrs");
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON_TYPE);
        client.path("persons/10");
        Person person = client.get(Person.class);
        assertNotNull(person);
    }

    @Test
    public void testCreateJson() {
        WebClient client = WebClient.create("http://localhost:8083/jaxrs");
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON_TYPE);
        client.path("persons");

        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");
        Response response = client.post(person);
        assertEquals(204, response.getStatus());
    }

    @Test
    public void testUpdateJson() {
        WebClient client = WebClient.create("http://localhost:8083/jaxrs");
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON_TYPE);
        client.path("persons/10");

        Person person = new Person();
        person.setId(Long.valueOf(10));
        person.setFirstName("firstName10-updated");
        person.setLastName("lastName10-updated");
        Response response = client.put(person);
        assertEquals(204, response.getStatus());
    }

    @Test
    public void testDeleteJson() {
        WebClient client = WebClient.create("http://localhost:8083/jaxrs");
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON_TYPE);
        client.path("persons/11");

        Response response = client.delete();
        assertEquals(204, response.getStatus());

    }

    // WebClient json
    @Test
    public void testListJsonp() throws IOException {
        WebClient client = WebClient.create("http://localhost:8083/jaxrs?_jsonp=testfunc");
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON_TYPE);
        client.path("persons");
        
        Response response = client.get();
        assertEquals(200, response.getStatus());
        InputStream in = (InputStream) response.getEntity();
        String strs = IOUtils.toString(in);
        assertTrue(strs.startsWith("testfunc"));
        System.out.println(strs);
    }

    @Test
    public void testGetJsonp() throws IOException {
        WebClient client = WebClient.create("http://localhost:8083/jaxrs?_jsonp=testfunc");
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON_TYPE);
        client.path("persons/10");
        
        Response response = client.get();
        assertEquals(200, response.getStatus());
        InputStream in = (InputStream) response.getEntity();
        String strs = IOUtils.toString(in);
        assertTrue(strs.startsWith("testfunc"));
        System.out.println(strs);
    }

}

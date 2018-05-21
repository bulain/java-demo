package com.bulain.hc;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bulain.rb.DiffBody;

public class HttpClientTest {
    private static HttpClient httpClient;
    private static HttpContext httpContext;

    @BeforeClass
    public static void setUp() {
        httpClient = new DefaultHttpClient();
        httpContext = new BasicHttpContext();
        BasicCookieStore basicCookieStore = new BasicCookieStore();
        httpContext.setAttribute(ClientContext.COOKIE_STORE, basicCookieStore);
    }

    @Test
    public void testNewRequest() throws ClientProtocolException, IOException, URISyntaxException {
        //execute post request
        HttpPost httpPost = new HttpPost("http://localhost:2082/api/review-requests/");

        String str = "bulain:bulain";
        String real = new String((new Base64()).encode((str).getBytes()), Charset.defaultCharset()).trim();

        httpPost.setHeader("Authorization", "Basic " + real);
        httpPost.setHeader("Accept", "application/json");

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("repository", "http://10.253.148.217/svn"));
        httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));

        HttpResponse response = httpClient.execute(httpPost, httpContext);
        int status = response.getStatusLine().getStatusCode();
        System.out.println(status);
        System.out.println(response);
        
        HttpEntity entity = response.getEntity();
        String string = EntityUtils.toString(entity);
        System.out.println(string);
        
        JSON json = JSONSerializer.toJSON(string);
        DynaBean dynaBean = (DynaBean)JSONSerializer.toJava(json);
        DynaBean request = (DynaBean)dynaBean.get("review_request");
        DynaBean links = (DynaBean)request.get("links");
        DynaBean draft = (DynaBean)links.get("draft");
        String draftHref = (String)draft.get("href");
        DynaBean diffs = (DynaBean)links.get("diffs");
        String diffsHref = (String)diffs.get("href");
        
        //execute put request
        HttpPut httpPut = new HttpPut(draftHref);
        httpPut.setHeader("Accept", "application/json");
        
        List<NameValuePair> listPut = new ArrayList<NameValuePair>();
        listPut.add(new BasicNameValuePair("summary", "test from java"));
        listPut.add(new BasicNameValuePair("description", "test from java by bulain"));
        httpPut.setEntity(new UrlEncodedFormEntity(listPut, "UTF-8"));
        
        response = httpClient.execute(httpPut, httpContext);
        status = response.getStatusLine().getStatusCode();
        System.out.println(status);
        System.out.println(response);
        
        entity = response.getEntity();
        string = EntityUtils.toString(entity);
        System.out.println(string);
        
        //execute post file
        httpPost = new HttpPost(diffsHref);
        httpPost.setHeader("Accept", "application/json");
        
        MultipartEntity multipart = new MultipartEntity();
        
        URL resource = this.getClass().getClassLoader().getResource("com/bulain/hc/diff.patch");
        File file = new File(resource.toURI());
        FileEntity fileEntity = new FileEntity(file, "UTF-8");
        multipart.addPart("basedir", new StringBody("/"));
        multipart.addPart("path", new DiffBody("diff", EntityUtils.toString(fileEntity)));
        httpPost.setEntity(multipart);
        
        response = httpClient.execute(httpPost, httpContext);
        status = response.getStatusLine().getStatusCode();
        System.out.println(status);
        System.out.println(response);
        
        entity = response.getEntity();
        string = EntityUtils.toString(entity);
        System.out.println(string);
    }
}

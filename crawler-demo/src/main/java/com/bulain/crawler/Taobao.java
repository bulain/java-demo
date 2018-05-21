package com.bulain.crawler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class Taobao {

    public static void main(String[] args) {
        Taobao taobao = new Taobao();

        try {
            //taobao.runTown("441581");
            taobao.printDistrictTaobao();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //1434088420811_8390
    //1434086109822_7527
    //private String format = "http://lsp.wuliu.taobao.com/locationservice/addr/output_address_town_array.do?l1=130000&l2=130100&l3=130102&lang=zh-S&_ksTS=1434089277457_8669&callback=jsonp";
    private String format = "http://lsp.wuliu.taobao.com/locationservice/addr/output_address_town_array.do?l1=%s&l2=%s&l3=%s&lang=zh-S&_ksTS=1434086109823_7529&callback=jsonp7530";
    private HttpClient client = HttpClientBuilder.create().build();
    private void runTown(String d) throws Exception {

        FileWriter writer = new FileWriter("target/town.js", true);

        String p = d.substring(0, 2) + "0000";
        String c = d.substring(0, 4) + "00";

        String url = String.format(format, p, c, d);
        HttpGet req = new HttpGet(url);

        HttpResponse resp = client.execute(req);

        HttpEntity entity = resp.getEntity();
        String text = null;
        if (entity != null) {
            text = EntityUtils.toString(entity, "UTF-8");
        }

        System.out.println(text);
        
        int start = text.indexOf("[");
        int end = text.indexOf("});");
        if (start > 0 && end > 0) {
            String json = text.substring(start, end);

            writer.write(json + "\n");
            writer.flush();
        }

    }

    private void printDistrict() throws Exception {
        FileReader fileReader = new FileReader("target/district.0.txt");
        BufferedReader reader = new BufferedReader(fileReader);
        String line = null;
        while ((line = reader.readLine()) != null) {
            String d = line.substring(0, 6);

            if (d.compareTo("0") < 0) {
                continue;
            }

            runTown(d);

            Thread.sleep(100);
        }
        reader.close();

    }
    
    private void printDistrictTaobao() throws Exception {
        FileReader fileReader = new FileReader("target/addr.js");
        BufferedReader reader = new BufferedReader(fileReader);
        String line = null;
        while ((line = reader.readLine()) != null) {
            if(line.contains(",")){
                continue;
            }
            
            System.out.println(line);
            
            String d = line.substring(0, 6);
            
            //if (d.compareTo("0") < 0) {
            //    continue;
            //}

            runTown(d);

            Thread.sleep(100);
        }
        reader.close();

    }
    
    

}

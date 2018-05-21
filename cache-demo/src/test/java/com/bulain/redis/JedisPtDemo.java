package com.bulain.redis;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import redis.clients.jedis.Jedis;

public class JedisPtDemo {
    private static Jedis jedis;

    @BeforeClass
    public static void setUpClass() {
        jedis = new Jedis("localhost");
    }

    @AfterClass
    public static void tearDownClass() {
        jedis.disconnect();
    }

    @Test
    public void testHsetHget() throws IOException {
        String[] files = new String[]{"data/100b.txt", "data/1k.txt", "data/10k.txt", "data/100k.txt", "data/1m.txt"};

        File dir = new File("target/data");
        dir.mkdirs();
        
        for (String file : files) {
            ClassPathResource resource = new ClassPathResource(file);
            InputStream is = resource.getInputStream();
            String data = IOUtils.toString(is);

            jedis.hset("test", file, data);

            String hget = jedis.hget("test", file);
            assertEquals(hget, data);

            OutputStream os = new FileOutputStream("target/" + file);
            IOUtils.write(data, os);
        }
    }

}

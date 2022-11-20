package com.bulain.redis;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import redis.clients.jedis.Jedis;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JedisPtDemo {
    private static Jedis jedis;

    @BeforeAll
    public static void setUpClass() {
        jedis = new Jedis("localhost");
    }

    @AfterAll
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

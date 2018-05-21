package com.bulain.redis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

public class Jedis1kPtDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(8);
        //JedisPool pool = new JedisPool("redis://localhost:6379/2");
        JedisPool pool = new JedisPool(config, "localhost", 6379, Protocol.DEFAULT_TIMEOUT, null, 2);

        int nThreads = 8;
        int times = 10000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        CountDownLatch latch = new CountDownLatch(nThreads * 4);

        ClassPathResource resource = new ClassPathResource("data/1k.txt");
        InputStream is = resource.getInputStream();
        String value = IOUtils.toString(is);

        for (int i = 0; i < nThreads; i++) {
            executorService.submit(new PtCallable("hset", pool, times, value, latch));
            executorService.submit(new PtCallable("hget", pool, times, value, latch));
            executorService.submit(new PtCallable("set", pool, times, value, latch));
            executorService.submit(new PtCallable("get", pool, times, value, latch));
        }

        latch.await();

        executorService.shutdown();

    }

}

class PtCallable implements Callable<Integer> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private CountDownLatch latch;
    private String cmd;
    private JedisPool pool;
    private int times;
    private String value;

    public PtCallable(String cmd, JedisPool pool, int times, String value, CountDownLatch latch) {
        this.cmd = cmd;
        this.pool = pool;
        this.times = times;
        this.value = value;
        this.latch = latch;
    }

    public Integer call() throws Exception {
        logger.info("{}-start", Thread.currentThread());

        String threadName = Thread.currentThread().getName();
        Date startAt = new Date();

        try {
            Jedis jedis = pool.getResource();
            for (int j = 0; j < times; j++) {
                String hkey = "hkey";
                String key = threadName + "-" + j;

                if ("hset".equals(cmd)) {
                    jedis.hset(hkey, key, value);
                } else if ("hget".equals(cmd)) {
                    jedis.hget(hkey, key);
                } else if ("set".equals(cmd)) {
                    jedis.set(key, value);
                } else if ("get".equals(cmd)) {
                    jedis.get(key);
                }
            }
            jedis.close();
        } catch (Exception e) {
            logger.error("call()-", e);
        }

        Date endAt = new Date();
        double during = (endAt.getTime() - startAt.getTime()) / 1000d;
        logger.info("{}-end : {}s", Thread.currentThread(), during);

        latch.countDown();

        return 0;
    }

}

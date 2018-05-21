package com.bulain.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/springContext-redis.xml"})
public class SetGetDemo {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void testJedisPool() throws InterruptedException {
        int nThreads = 4;
        int times = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        CountDownLatch latch = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            executorService.submit(new SetGetThead(jedisPool, times, latch));
        }

        latch.await();
        executorService.shutdownNow();
    }
}

class SetGetThead implements Callable<Integer> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JedisPool jedisPool;
    private int times;
    private CountDownLatch latch;

    public SetGetThead(JedisPool jedisPool, int times, CountDownLatch latch) {
        this.jedisPool = jedisPool;
        this.times = times;
        this.latch = latch;
    }

    public Integer call() throws Exception {

        String name = Thread.currentThread().getName();

        try {
            for (int i = 0; i < times; i++) {
                Jedis jedis = jedisPool.getResource();
                try {
                    jedis.hset(name, "ABC", Integer.toString(i));
                    logger.debug("hset({},{})={}", new Object[]{name, "ABC", i});
                } finally {
                    jedis.close();
                }

                jedis = jedisPool.getResource();
                try {
                    String value = jedis.hget(name, "ABC");
                    logger.debug("hget({},{})={}", new Object[]{name, "ABC", value});
                } finally {
                    jedis.close();
                }

            }
        } catch (Exception e) {
            logger.error("call()-", e);
        }

        latch.countDown();

        return 0;
    }

}

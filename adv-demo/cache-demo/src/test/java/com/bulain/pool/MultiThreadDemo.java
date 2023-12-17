package com.bulain.pool;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:spring/springContext-redis.xml"})
public class MultiThreadDemo {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void testJedisPool() throws InterruptedException {
        int nThreads = 4;
        int times = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        CountDownLatch latch = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            executorService.submit(new WorkerThead(jedisPool, times, latch));
        }

        latch.await();
        executorService.shutdownNow();
    }
}

class WorkerThead implements Callable<Integer> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JedisPool jedisPool;
    private int times;
    private CountDownLatch latch;

    public WorkerThead(JedisPool jedisPool, int times, CountDownLatch latch) {
        this.jedisPool = jedisPool;
        this.times = times;
        this.latch = latch;
    }

    public Integer call() throws Exception {

        try {
            for (int i = 0; i < times; i++) {
                Jedis jedis = jedisPool.getResource();
                try {
                    jedis.get("key");

                    logger.debug("{}\t{}", this, jedis);
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

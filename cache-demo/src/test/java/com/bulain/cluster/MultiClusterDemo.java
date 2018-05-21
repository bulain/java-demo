package com.bulain.cluster;

import static org.junit.Assert.assertEquals;

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

import redis.clients.jedis.JedisCluster;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/springContext-cluster.xml"})
public class MultiClusterDemo {

    @Autowired
    private JedisCluster jedisCluster;

    @Test
    public void testCluster() throws InterruptedException {
        int nThreads = 4;
        int times = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        CountDownLatch latch = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            executorService.submit(new WorkerThead(jedisCluster, times, latch));
        }

        latch.await();
        executorService.shutdownNow();
    }

}

class WorkerThead implements Callable<Integer> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JedisCluster jedisCluster;
    private int times;
    private CountDownLatch latch;

    public WorkerThead(JedisCluster jedisCluster, int times, CountDownLatch latch) {
        this.jedisCluster = jedisCluster;
        this.times = times;
        this.latch = latch;
    }

    public Integer call() throws Exception {
        try {
            for (int i = 0; i < times; i++) {
                jedisCluster.set("key-" + i, Integer.toString(i));

                logger.debug("{}\tset {}", this, i);
            }
            for (int i = 0; i < times; i++) {
                String val = jedisCluster.get("key-" + i);
                assertEquals(Integer.toString(i), val);

                logger.debug("{}\tget {}", this, val);
            }
        } catch (Exception e) {
            logger.error("call()-", e);
        }

        latch.countDown();
        return 0;
    }

}

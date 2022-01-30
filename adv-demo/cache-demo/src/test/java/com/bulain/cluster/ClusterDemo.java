package com.bulain.cluster;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.JedisCluster;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/springContext-cluster.xml"})
public class ClusterDemo {

    @Autowired
    private JedisCluster jedisCluster;

    @Test
    public void testCluster() {
        int size = 1000;
        for (int i = 0; i < size; i++) {
            jedisCluster.set("key-" + i, Integer.toString(i));
        }
        for (int i = 0; i < size; i++) {
            String val = jedisCluster.get("key-" + i);
            assertEquals(Integer.toString(i), val);
        }
    }
    
}

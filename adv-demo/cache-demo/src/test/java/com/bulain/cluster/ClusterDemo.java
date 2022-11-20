package com.bulain.cluster;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import redis.clients.jedis.JedisCluster;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
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

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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:spring/springContext-redis.xml"})
public class JedisPoolDemo {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void testJedisPool() {
        for (int i = 0; i < 100; i++) {
            Jedis jedis = jedisPool.getResource();
            try {
                jedis.get("key");
                logger.debug("{}", jedis);
            } finally {
                jedis.close();
            }
        }
    }

}

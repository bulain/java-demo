package com.bulain.redis;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

public class PubSubDemo {
    private static JedisPool pool;
    private static String channel = "/bulain/channel";

    @BeforeClass
    public static void setUpClass() {
        pool = new JedisPool("localhost");

        new Thread() {
            @Override
            public void run() {
                try {
                    Jedis jedis = pool.getResource();
                    JedisPubSub jedisPubSub = new Sub();
                    jedis.subscribe(jedisPubSub, channel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @AfterClass
    public static void tearDownClass() {
        pool.destroy();
    }

    @Test
    public void testPub() {
        Jedis jedis = pool.getResource();
        for (int i = 0; i < 10; i++) {
            jedis.publish(channel, String.format("test-%s", i));
        }
        jedis.close();
    }

    static class Sub extends JedisPubSub {
        @Override
        public void onUnsubscribe(String channel, int subscribedChannels) {
        }
        @Override
        public void onSubscribe(String channel, int subscribedChannels) {
        }
        @Override
        public void onPUnsubscribe(String pattern, int subscribedChannels) {
        }
        @Override
        public void onPSubscribe(String pattern, int subscribedChannels) {
        }
        @Override
        public void onPMessage(String pattern, String channel, String message) {
        }
        @Override
        public void onMessage(String channel, String message) {
            System.out.printf("%s:\t%s\n", channel, message);
        }
    }
}

package com.bulain.weixin;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.bulain.weixin.pojo.Rpc;
import com.bulain.weixin.ws.RpcWs;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring-test/applicationContext*.xml",
        "classpath*:spring/applicationContext*.xml"})
public class RpcWsTest {
    @Autowired
    private RpcWs rpcWs;

    @Test
    public void testGet() {
        String signature = "abc";
        String timestamp = "111";
        String nonce = "1234";
        String echostr = "test echo";
        String echo = rpcWs.get(signature, timestamp, nonce, echostr);

        assertEquals(echo, echostr);
    }

    @Test
    public void testPost(){
        String signature = "abc";
        String timestamp = "111";
        String nonce = "1234";
        Rpc rpc = new Rpc();
        rpc.setToUserName("toUserName");
        rpc.setFromUserName("fromUserName");
        rpc.setCreateTime(1L);
        rpc.setMsgType("msgType");
        rpc.setContent("content");
        rpc.setMsgId("msgId");
        
        rpcWs.post(signature, timestamp, nonce, rpc );
    }
    
}

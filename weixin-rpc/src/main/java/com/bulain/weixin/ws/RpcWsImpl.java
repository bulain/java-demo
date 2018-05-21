package com.bulain.weixin.ws;

import com.bulain.weixin.pojo.Rpc;


public class RpcWsImpl implements RpcWs {

    public String get(String signature, String timestamp, String nonce, String echostr) {
        return echostr;
    }

    public String post(String signature, String timestamp, String nonce, Rpc rpc) {
        return null;
    }

}

package com.bulain.weixin.ws;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.bulain.weixin.pojo.Rpc;

@Path("/rpc/")
public interface RpcWs {
    @GET
    @Path("echo")
    String get(@QueryParam("signature") String signature, @QueryParam("timestamp") String timestamp,
            @QueryParam("nonce") String nonce, @QueryParam("echostr") String echostr);

    @POST
    @Path("echo")
    String post(@QueryParam("signature") String signature, @QueryParam("timestamp") String timestamp,
            @QueryParam("nonce") String nonce, Rpc rpc);

}

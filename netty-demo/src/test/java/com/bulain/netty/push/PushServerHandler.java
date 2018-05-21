package com.bulain.netty.push;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.netty.push.pojo.Printer;
import com.bulain.netty.push.pojo.Stop;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class PushServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(PushServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof Printer) {
            Printer printer = (Printer) msg;
            if (PushChannels.containsKey(printer)) {
                logger.error("重复添加客户端:" + printer.getName());
                ctx.writeAndFlush(new Stop());
            } else {
                logger.info("添加客户端:" + printer.getName());
                Channel channel = ctx.channel();
                PushChannels.put(printer, channel);
            }
        }
    }

}

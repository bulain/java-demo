package com.bulain.netty.push;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.netty.push.pojo.Printer;
import com.bulain.netty.push.pojo.Report;
import com.bulain.netty.push.pojo.Stop;

public class PushClientHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(PushClientHandler.class);

    private Printer printer;

    public PushClientHandler(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(printer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Stop) {
            logger.error("重复添加客户端:" + printer.getName());
            printer.setActive(false);
            ctx.close();
        } else if (msg instanceof Report) {
            Report report = (Report) msg;
            logger.debug("Report:" + report.getName());
        }
    }

}

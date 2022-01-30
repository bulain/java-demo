package com.bulain.netty.push;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bulain.netty.push.pojo.Printer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class PushClient {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

    @SuppressWarnings("static-access")
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            final int no = i;
            executorService.submit(new Callable<Object>() {
                public Object call() throws Exception {
                    Printer printer = Printer.createPrinter("TEST-" + no);
                    while (printer.isActive()) {
                        runTask(printer);
                        Thread.currentThread().sleep(1000 * 10);
                    }
                    return null;
                }
            });
        }

        Thread.currentThread().join();
    }

    public static void runTask(final Printer printer) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new ObjectEncoder());
                            p.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                            p.addLast(new PushClientHandler(printer));
                        }
                    });

            b.connect(HOST, PORT).sync().channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}

package com.bulain.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;

public class HttpServer {

    private static final int PORT = 81;
    
    public static void main(String[] args) throws Exception {

        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup work = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ChannelPipeline cp = ch.pipeline();
                            cp.addLast(new HttpServerCodec());
                            cp.addLast(new HttpObjectAggregator(65536));
                            cp.addLast(new SimpleChannelInboundHandler() {
                                private final AsciiString CONTENT_TYPE = AsciiString.cached("Content-Type");
                                private final AsciiString CONTENT_LENGTH = AsciiString.cached("Content-Length");
                                private final AsciiString CONNECTION = AsciiString.cached("Connection");
                                private final AsciiString KEEP_ALIVE = AsciiString.cached("keep-alive");
                                private final String content = "<html><body>hello word</body></html>";

                                @Override
                                public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    if (msg instanceof FullHttpRequest) {
                                        FullHttpRequest request = (FullHttpRequest) msg;

                                        HttpMethod method = request.method();
                                        if (HttpMethod.GET.equals(method)) {

                                        } else if (HttpMethod.POST.equals(method)) {

                                        }

                                        ByteBuf byteBuf = Unpooled.wrappedBuffer(content.getBytes());
                                        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
                                        response.headers().set(CONTENT_TYPE, "text/html");
                                        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());

                                        boolean keepAlive = HttpUtil.isKeepAlive(request);
                                        if (!keepAlive) {
                                            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
                                        } else {
                                            response.headers().set(CONNECTION, KEEP_ALIVE);
                                            ctx.writeAndFlush(response);
                                        }

                                    }

                                }
                            });
                        }
                    });

            ChannelFuture channel = bootstrap.bind(PORT).sync();

            channel.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }

}

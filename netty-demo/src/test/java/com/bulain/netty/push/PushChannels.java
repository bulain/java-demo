package com.bulain.netty.push;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.bulain.netty.push.pojo.Printer;

public class PushChannels {
    private static Map<Printer, Channel> printers = new ConcurrentHashMap<Printer, Channel>();

    public static boolean containsKey(Printer key){
        return printers.containsKey(key);
    }
    
    public static void put(Printer key, Channel value){
        printers.put(key, value);
    }
    
    public static ChannelFuture writeAndFlush(Printer printer, Object msg) {
        ChannelFuture future = null;
        Channel channel = printers.get(printer);
        if (channel != null && channel.isWritable()) {
            future = channel.writeAndFlush(msg);
        }
        return future;
    }

}

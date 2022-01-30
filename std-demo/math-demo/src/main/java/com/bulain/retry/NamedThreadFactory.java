package com.bulain.retry;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {
    private static final AtomicInteger POOL = new AtomicInteger(1);

    private final AtomicInteger threadNo = new AtomicInteger(1);

    private final String prefix;
    private final boolean daemon;
    private final ThreadGroup threadGroup;

    public NamedThreadFactory() {
        this("pool-" + POOL.getAndIncrement(), false);
    }

    public NamedThreadFactory(String prefix) {
        this(prefix, false);
    }

    public NamedThreadFactory(String prefix, boolean daemo) {
        this.prefix = prefix + "-thread-";
        this.daemon = daemo;
        SecurityManager s = System.getSecurityManager();
        threadGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }

    public Thread newThread(Runnable runnable) {
        String name = prefix + threadNo.getAndIncrement();
        Thread ret = new Thread(threadGroup, runnable, name, 0);
        ret.setDaemon(daemon);
        return ret;
    }

    public ThreadGroup getThreadGroup() {
        return threadGroup;
    }

}

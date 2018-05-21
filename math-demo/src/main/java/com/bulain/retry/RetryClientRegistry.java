package com.bulain.retry;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetryClientRegistry {
    private static final Logger logger = LoggerFactory.getLogger(RetryClientRegistry.class);

    // 重连检测周期3秒(单位毫秒)
    private static final int RECONNECT_PERIOD_DEFAULT = 3 * 1000;

    // 定时任务执行器
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1,
            new NamedThreadFactory("timer", true));

    // 重连定时器，定时检查连接是否可用，不可用时，无限次重连
    private final ScheduledFuture<?> reconnectFuture;

    // 客户端获取过程锁，锁定客户端实例的创建过程，防止重复的客户端
    private final ReentrantLock clientLock = new ReentrantLock();

    // 线程安全型客户端
    private final Object client;

    public RetryClientRegistry(Object client) {
        this.client = client;

        // 启动重连定时器
        int reconnectPeriod = RECONNECT_PERIOD_DEFAULT;
        reconnectFuture = scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                // 检测并连接注册中心
                try {
                    connect();
                } catch (Throwable t) { // 防御性容错
                    logger.error("Unexpected error occur at reconnect, cause: " + t.getMessage(), t);
                }
            }

        }, reconnectPeriod, reconnectPeriod, TimeUnit.MILLISECONDS);

    }
    protected final void connect() {
        try {
            // 检查是否已连接
            if (isAvailable()) {
                return;
            }

            logger.info("Reconnect to server ...");

            clientLock.lock();
            try {
                // 双重检查是否已连接
                if (isAvailable()) {
                    return;
                }
                recover();
            } finally {
                clientLock.unlock();
            }
        } catch (Throwable t) { // 忽略所有异常，等待下次重试
            logger.error("Failed to connect to server ", t);
        }
    }

    private void recover() {
        //TODO client recover
    }

    private boolean isAvailable() {
        //TODO client available
        return false;
    }

    public void destroy() {
        try {
            // 取消重连定时器
            if (!reconnectFuture.isCancelled()) {
                reconnectFuture.cancel(true);
            }
        } catch (Throwable t) {
            logger.warn("Failed to cancel reconnect timer", t);
        }
    }

}

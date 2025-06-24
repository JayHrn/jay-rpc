package com.jayhrn.jayrpc.fault.retry;

import com.jayhrn.jayrpc.spi.SpiLoader;

/**
 * 重试策略工厂（用于获取重试策略对象）
 *
 * @Author JayHrn
 * @Date 2025/6/24 16:03
 * @Version 1.0
 */
public class RetryStrategyFactory {

    // SPI 动态加载
    static {
        SpiLoader.load(RetryStrategy.class);
    }

    /**
     * 默认重试策略
     */
    private static final RetryStrategy DEFAULT_RETRY_STRATEGY = new NoRetryStrategy();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static RetryStrategy getInstance(String key) {
        return SpiLoader.getInstance(RetryStrategy.class, key);
    }
}

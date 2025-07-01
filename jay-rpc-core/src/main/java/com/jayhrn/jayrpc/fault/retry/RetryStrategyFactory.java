package com.jayhrn.jayrpc.fault.retry;

import com.jayhrn.jayrpc.spi.SpiLoader;
import lombok.extern.slf4j.Slf4j;

/**
 * 重试策略工厂（用于获取重试策略对象）
 *
 * @Author JayHrn
 * @Date 2025/6/24 16:03
 * @Version 1.0
 */
@Slf4j
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
        try {
            return SpiLoader.getInstance(RetryStrategy.class, key);
        } catch (RuntimeException e) {
            log.info("未找到对应的重试策略 key = [{}]，使用默认重试策略: {}", key, DEFAULT_RETRY_STRATEGY.getClass().getSimpleName());
            return DEFAULT_RETRY_STRATEGY;
        }
    }
}

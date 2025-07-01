package com.jayhrn.jayrpc.fault.tolerant;

import com.jayhrn.jayrpc.spi.SpiLoader;
import lombok.extern.slf4j.Slf4j;

/**
 * 容错策略工厂（工厂模式，用于获取容错策略对象）
 *
 * @Author JayHrn
 * @Date 2025/6/25 11:21
 * @Version 1.0
 */
@Slf4j
public class TolerantStrategyFactory {

    // SPI 动态加载
    static {
        SpiLoader.load(TolerantStrategy.class);
    }

    /**
     * 默认容错策略
     */
    private static final TolerantStrategy DEFAULT_TOLERANT_STRATEGY = new FailFastTolerantStrategy();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static TolerantStrategy getInstance(String key) {
        try {
            return SpiLoader.getInstance(TolerantStrategy.class, key);
        } catch (RuntimeException e) {
            log.info("未找到对应的容错策略 key = [{}]，使用默认容错策略: {}", key, DEFAULT_TOLERANT_STRATEGY.getClass().getSimpleName());
            return DEFAULT_TOLERANT_STRATEGY;
        }
    }
}
package com.jayhrn.jayrpc.fault.tolerant;

import com.jayhrn.jayrpc.spi.SpiLoader;

/**
 * 容错策略工厂（工厂模式，用于获取容错策略对象）
 *
 * @Author JayHrn
 * @Date 2025/6/25 11:21
 * @Version 1.0
 */
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
        return SpiLoader.getInstance(TolerantStrategy.class, key);
    }
}
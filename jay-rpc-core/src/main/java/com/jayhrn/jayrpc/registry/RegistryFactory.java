package com.jayhrn.jayrpc.registry;

import com.jayhrn.jayrpc.spi.SpiLoader;
import lombok.extern.slf4j.Slf4j;

/**
 * 注册中心工厂（用于获取注册中心对象）
 *
 * @Author JayHrn
 * @Date 2025/6/19 16:11
 * @Version 1.0
 */
@Slf4j
public class RegistryFactory {

    // SPI 动态加载
    static {
        SpiLoader.load(Registry.class);
    }

    /**
     * 默认注册中心
     */
    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Registry getInstance(String key) {
        try {
            return SpiLoader.getInstance(Registry.class, key);
        } catch (RuntimeException e) {
            log.info("未找到对应的注册中心 key = [{}]，使用默认注册中心: {}", key, DEFAULT_REGISTRY.getClass().getSimpleName());
            return DEFAULT_REGISTRY;
        }
    }
}

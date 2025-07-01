package com.jayhrn.jayrpc.serializer;

import com.jayhrn.jayrpc.spi.SpiLoader;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author JayHrn
 * @Date 2025/6/17 19:06
 * @Version 1.0
 */
@Slf4j
public class SerializerFactory {

    // SPI 动态加载
    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * 获取实例
     *
     * @param serializerKey 序列化器键
     * @return Serializer
     */
    public static Serializer getInstance(String serializerKey) {
        try {
            return SpiLoader.getInstance(Serializer.class, serializerKey);
        } catch (RuntimeException e) {
            // 使用默认序列化器
            log.info("未找到对应的序列化器 key = [{}]，使用默认序列化器: {}", serializerKey, DEFAULT_SERIALIZER.getClass().getSimpleName());
            return DEFAULT_SERIALIZER;
        }
    }
}

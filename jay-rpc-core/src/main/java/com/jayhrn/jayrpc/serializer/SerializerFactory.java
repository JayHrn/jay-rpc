package com.jayhrn.jayrpc.serializer;

import com.jayhrn.jayrpc.spi.SpiLoader;

/**
 * @Author JayHrn
 * @Date 2025/6/17 19:06
 * @Version 1.0
 */
public class SerializerFactory {
//    private static final Map<String, Serializer> KEY_SERIALIZER_MAP = new HashMap<String, Serializer>() {{
//        put(SerializerKeys.JDK, new JdkSerializer());
//        put(SerializerKeys.JSON, new JsonSerializer());
//        put(SerializerKeys.KRYO, new KryoSerializer());
//        put(SerializerKeys.HESSIAN, new HessianSerializer());
//    }};

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
//        return KEY_SERIALIZER_MAP.getOrDefault(serializerKey, DEFAULT_SERIALIZER);
        return SpiLoader.getInstance(Serializer.class, serializerKey);
    }
}

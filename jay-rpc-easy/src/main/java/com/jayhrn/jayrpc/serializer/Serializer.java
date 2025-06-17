package com.jayhrn.jayrpc.serializer;

import java.io.IOException;

/**
 * 序列化器接口
 *
 * @Author JayHrn
 * @Date 2025/6/15 17:12
 * @Version 1.0
 */
public interface Serializer {
    /**
     * 序列化
     *
     * @param obj 对象
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> byte[] serialize(T obj) throws IOException;

    /**
     * 反序列化
     *
     * @param bytes
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;
}

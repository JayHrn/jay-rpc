package com.jayhrn.jayrpc.fault.tolerant;

import com.jayhrn.jayrpc.model.RpcResponse;

import java.util.Map;

/**
 * 容错策略
 *
 * @Author JayHrn
 * @Date 2025/6/25 10:26
 * @Version 1.0
 */
public interface TolerantStrategy {

    /**
     * 容错
     *
     * @param context 上下文，用于传递数据
     * @param e       异常
     * @return
     */
    RpcResponse doTolerant(Map<String, Object> context, Exception e);
}

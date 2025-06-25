package com.jayhrn.jayrpc.fault.tolerant;

import com.jayhrn.jayrpc.model.RpcResponse;

import java.util.Map;

/**
 * 故障转移 - 容错服务（转移到其他服务节点）
 *
 * @Author JayHrn
 * @Date 2025/6/25 11:12
 * @Version 1.0
 */
public class FailOverTolerantStrategy implements TolerantStrategy {

    /**
     * 容错
     *
     * @param context 上下文，用于传递数据
     * @param e       异常
     * @return
     */
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        // TODO: 可自行实现，获取其他服务节点并调用
        return null;
    }
}

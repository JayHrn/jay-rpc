package com.jayhrn.jayrpc.fault.tolerant;

import com.jayhrn.jayrpc.model.RpcResponse;

import java.util.Map;

/**
 * 快速失败 - 容错策略（立即通知外层调用方）
 *
 * @Author JayHrn
 * @Date 2025/6/25 10:32
 * @Version 1.0
 */
public class FailFastTolerantStrategy implements TolerantStrategy {

    /**
     * 容错
     *
     * @param context 上下文，用于传递数据
     * @param e       异常
     * @return
     */
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        throw new RuntimeException("服务报错", e);
    }
}

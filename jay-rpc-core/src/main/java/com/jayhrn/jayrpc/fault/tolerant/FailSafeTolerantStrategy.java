package com.jayhrn.jayrpc.fault.tolerant;

import com.jayhrn.jayrpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 静默处理异常 - 容错策略
 *
 * @Author JayHrn
 * @Date 2025/6/25 10:36
 * @Version 1.0
 */
@Slf4j
public class FailSafeTolerantStrategy implements TolerantStrategy {

    /**
     * 容错
     *
     * @param context 上下文，用于传递数据
     * @param e       异常
     * @return
     */
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        log.info("静默处理异常", e);
        return new RpcResponse();
    }
}

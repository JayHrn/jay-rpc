package com.jayhrn.jayrpc.fault.retry;

import com.jayhrn.jayrpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * 重试策略
 *
 * @Author JayHrn
 * @Date 2025/6/24 15:08
 * @Version 1.0
 */
public interface RetryStrategy {

    /**
     * 重试
     *
     * @param callable
     * @return
     * @throws Exception
     */
    RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception;
}

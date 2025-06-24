package com.jayhrn.jayrpc.fault.retry;

import com.jayhrn.jayrpc.model.RpcResponse;
import org.junit.Test;

/**
 * 重试策略测试
 *
 * @Author JayHrn
 * @Date 2025/6/24 15:33
 * @Version 1.0
 */
public class RetryStrategyTest {
    private final RetryStrategy retryStrategy = new FixedIntervalRetryStrategy();

    @Test
    public void doRetry() {
        try {
            RpcResponse rpcResponse = retryStrategy.doRetry(() -> {
                System.out.println("测试重试");
                throw new RuntimeException("模拟重试失败");
            });
            System.out.println(rpcResponse);
        } catch (Exception e) {
            System.out.println("重试多次失败");
            e.printStackTrace();
        }
    }
}

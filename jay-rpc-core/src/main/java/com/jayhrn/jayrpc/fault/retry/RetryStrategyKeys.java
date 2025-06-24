package com.jayhrn.jayrpc.fault.retry;

/**
 * 重试策略键名常量
 *
 * @Author JayHrn
 * @Date 2025/6/24 15:59
 * @Version 1.0
 */
public interface RetryStrategyKeys {

    /**
     * 不重试
     */
    String NO = "no";

    /**
     * 固定时间间隔重试
     */
    String FIXED_INTERVAL = "fixedInterval";
}

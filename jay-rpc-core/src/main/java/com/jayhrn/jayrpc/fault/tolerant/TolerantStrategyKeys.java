package com.jayhrn.jayrpc.fault.tolerant;

/**
 * 容错策略键名常量
 *
 * @Author JayHrn
 * @Date 2025/6/25 11:15
 * @Version 1.0
 */
public interface TolerantStrategyKeys {

    /**
     * 快速失败（抛出异常）
     */
    String FAIL_FAST = "failFast";

    /**
     * 静默处理（打印日志）
     */
    String FAIL_SAFE = "failSafe";

    /**
     * 故障恢复（降级到其他服务）
     */
    String FAIL_BACK = "failBack";

    /**
     * 故障转移（转移到其他服务）
     */
    String FAIL_OVER = "failOver";
}

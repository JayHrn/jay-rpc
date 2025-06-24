package com.jayhrn.jayrpc.loadbalancer;

/**
 * 负载均衡器键名常量
 *
 * @Author JayHrn
 * @Date 2025/6/23 17:35
 * @Version 1.0
 */
public interface LoadBalancerKeys {

    /**
     * 轮询
     */
    String ROUND_ROBIN = "roundRobin";
    
    /**
     * 随机
     */
    String RANDOM = "random";

    /**
     * 一致性哈希
     */
    String CONSISTENT_HASH = "consistentHash";
}

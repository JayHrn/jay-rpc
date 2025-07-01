package com.jayhrn.jayrpc.loadbalancer;

import com.jayhrn.jayrpc.spi.SpiLoader;
import lombok.extern.slf4j.Slf4j;

/**
 * 负载均衡器工厂（工厂模式，用于获取负载均衡器对象）
 *
 * @Author JayHrn
 * @Date 2025/6/23 17:38
 * @Version 1.0
 */
@Slf4j
public class LoadBalancerFactory {

    // SPI 动态加载
    static {
        SpiLoader.load(LoadBalancer.class);
    }

    /**
     * 默认负载均衡器
     */
    private static final LoadBalancer DEFAULT_LOAD_BALANCER = new RoundRobinLoadBalancer();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static LoadBalancer getInstance(String key) {
        try {
            return SpiLoader.getInstance(LoadBalancer.class, key);
        } catch (RuntimeException e) {
            log.info("未找到对应的负载均衡器 key = [{}]，使用负载均衡器: {}", key, DEFAULT_LOAD_BALANCER.getClass().getSimpleName());
            return DEFAULT_LOAD_BALANCER;
        }
    }
}

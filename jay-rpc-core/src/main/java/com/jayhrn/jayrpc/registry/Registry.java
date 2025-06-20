package com.jayhrn.jayrpc.registry;

import com.jayhrn.jayrpc.config.RegistryConfig;
import com.jayhrn.jayrpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * 注册中心
 *
 * @Author JayHrn
 * @Date 2025/6/19 15:33
 * @Version 1.0
 */
public interface Registry {
    /**
     * 初始化
     *
     * @param registryConfig
     */
    void init(RegistryConfig registryConfig);

    /**
     * 注册服务（服务端）
     *
     * @param serviceMetaInfo
     * @throws Exception
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws Exception;

    /**
     * 注销服务（服务端）
     *
     * @param serviceMetaInfo
     */
    void unregister(ServiceMetaInfo serviceMetaInfo);

    /**
     * 服务发现（获取某服务端所有节点，消费端）
     *
     * @param serviceKey 服务键名
     * @return
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);

    /**
     * 心跳检测（服务端）
     */
    void heartBeat();

    /**
     * 监听（消费端）
     *
     * @param serviceKey 服务键名
     */
    void watch(String serviceKey);

    /**
     * 服务销毁
     */
    void destroy();
}

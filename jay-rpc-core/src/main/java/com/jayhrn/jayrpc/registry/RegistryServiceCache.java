package com.jayhrn.jayrpc.registry;

import com.jayhrn.jayrpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册中心服务本地缓存（支持多个服务）
 *
 * @Author JayHrn
 * @Date 2025/6/20 14:55
 * @Version 1.0
 */
public class RegistryServiceCache {
    /**
     * 服务缓存
     */
    Map<String, List<ServiceMetaInfo>> serviceCache = new ConcurrentHashMap<>();

    /**
     * 写缓存
     *
     * @param serviceKey      服务键名
     * @param newServiceCache 更新后的缓存列表
     */
    void writeCache(String serviceKey, List<ServiceMetaInfo> newServiceCache) {
        this.serviceCache.put(serviceKey, newServiceCache);
    }

    /**
     * 读缓存
     *
     * @param serviceKey 服务键名
     * @return
     */
    List<ServiceMetaInfo> readCache(String serviceKey) {
        return this.serviceCache.get(serviceKey);
    }

    /**
     * 清空缓存
     *
     * @param serviceKey 服务键名
     */
    void clearCache(String serviceKey) {
        this.serviceCache.remove(serviceKey);
    }
}

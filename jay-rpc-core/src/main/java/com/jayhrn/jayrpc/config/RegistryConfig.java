package com.jayhrn.jayrpc.config;

import com.jayhrn.jayrpc.registry.RegistryKeys;
import lombok.Data;

/**
 * RPC 框架注册中心配置
 *
 * @Author JayHrn
 * @Date 2025/6/19 15:11
 * @Version 1.0
 */
@Data
public class RegistryConfig {
    /**
     * 注册中心类别
     */
    private String registry = RegistryKeys.ETCD;
    /**
     * 注册中心地址
     */
    private String address = "http://<etcd ip>:2379";
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 超时时间（单位毫秒）
     */
    private Long timeout = 10000L;
}

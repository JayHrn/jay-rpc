package com.jayhrn.jayrpc.config;

import lombok.Data;

/**
 * RPC 框架全局配置
 *
 * @Author JayHrn
 * @Date 2025/6/17 14:11
 * @Version 1.0
 */
@Data
public class RpcConfig {
    /**
     * 名称
     */
    private String name = "jay-rpc";
    /**
     * 版本号
     */
    private String version = "1.0.0";
    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";
    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;
    /**
     * 模拟调用
     */
    private boolean mock = false;
}

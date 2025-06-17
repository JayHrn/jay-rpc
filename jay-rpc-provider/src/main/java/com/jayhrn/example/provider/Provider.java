package com.jayhrn.example.provider;

import com.jayhrn.example.service.UserService;
import com.jayhrn.jayrpc.RpcApplication;
import com.jayhrn.jayrpc.config.RpcConfig;
import com.jayhrn.jayrpc.registry.LocalRegistry;
import com.jayhrn.jayrpc.server.HttpServer;
import com.jayhrn.jayrpc.server.VertxHttpServer;

/**
 * 服务提供者示例
 *
 * @Author JayHrn
 * @Date 2025/6/17 15:09
 * @Version 1.0
 */
public class Provider {
    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        // 注册服务
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(rpcConfig.getServerPort());
    }
}

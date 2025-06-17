package com.jayhrn.example.provider;

import com.jayhrn.example.service.UserService;
import com.jayhrn.jayrpc.registry.LocalRegistry;
import com.jayhrn.jayrpc.server.VertxHttpServer;

/**
 * 服务提供者
 *
 * @Author JayHrn
 * @Date 2025/6/15 16:33
 * @Version 1.0
 */
public class EasyProvider {
    public static void main(String[] args) {
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动web服务器
        VertxHttpServer vertxHttpServer = new VertxHttpServer();
        vertxHttpServer.doStart(8080);
    }
}

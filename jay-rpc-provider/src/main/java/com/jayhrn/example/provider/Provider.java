package com.jayhrn.example.provider;

import com.jayhrn.example.service.UserService;
import com.jayhrn.jayrpc.RpcApplication;
import com.jayhrn.jayrpc.config.RegistryConfig;
import com.jayhrn.jayrpc.config.RpcConfig;
import com.jayhrn.jayrpc.model.ServiceMetaInfo;
import com.jayhrn.jayrpc.registry.LocalRegistry;
import com.jayhrn.jayrpc.registry.Registry;
import com.jayhrn.jayrpc.registry.RegistryFactory;
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

        // 注册服务，这里需要服务后来接收的请求找到对应的实现类
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 注册服务到注册中心（服务名称就是服务接口名称）
        String serviceName = UserService.class.getName();

        // 获取RPC配置信息
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        // 获取注册中心配置信息
        RegistryConfig registerConfig = rpcConfig.getRegistryConfig();
        // 获取指定类型的注册中心
        Registry registry = RegistryFactory.getInstance(registerConfig.getRegistry());
        // 注册服务信息
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());

        try {
            // 注册服务到注册中心
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(rpcConfig.getServerPort());
    }
}

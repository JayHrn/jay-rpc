package com.jayhrn.jayrpc.bootstrap;

import com.jayhrn.jayrpc.RpcApplication;
import com.jayhrn.jayrpc.config.RegistryConfig;
import com.jayhrn.jayrpc.config.RpcConfig;
import com.jayhrn.jayrpc.model.ServiceMetaInfo;
import com.jayhrn.jayrpc.model.ServiceRegisterInfo;
import com.jayhrn.jayrpc.registry.LocalRegistry;
import com.jayhrn.jayrpc.registry.Registry;
import com.jayhrn.jayrpc.registry.RegistryFactory;
import com.jayhrn.jayrpc.server.tcp.VertxTcpServer;

import java.util.List;

/**
 * 服务提供者启动类（初始化）
 *
 * @Author JayHrn
 * @Date 2025/6/25 20:21
 * @Version 1.0
 */
public class ProviderBootstrap {
    /**
     * 初始化
     *
     * @param serviceRegisterInfoList 注册服务列表
     */
    public static void init(List<ServiceRegisterInfo<?>> serviceRegisterInfoList) {
        // RPC 框架初始化
        RpcApplication.init();
        // RPC全局配置信息
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        // 注册服务
        for (ServiceRegisterInfo<?> serviceRegisterInfo : serviceRegisterInfoList) {
            // 服务名称
            String serviceName = serviceRegisterInfo.getServiceName();
            // 注册服务，这里需要服务后来接收的请求找到对应的实现类
            LocalRegistry.register(serviceName, serviceRegisterInfo.getImplClass());

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
        }

        // 启动 TCP 服务
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(8080);
    }
}

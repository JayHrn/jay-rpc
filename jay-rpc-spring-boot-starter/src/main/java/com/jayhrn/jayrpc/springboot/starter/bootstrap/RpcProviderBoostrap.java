package com.jayhrn.jayrpc.springboot.starter.bootstrap;

import com.jayhrn.jayrpc.RpcApplication;
import com.jayhrn.jayrpc.config.RegistryConfig;
import com.jayhrn.jayrpc.config.RpcConfig;
import com.jayhrn.jayrpc.model.ServiceMetaInfo;
import com.jayhrn.jayrpc.registry.LocalRegistry;
import com.jayhrn.jayrpc.registry.Registry;
import com.jayhrn.jayrpc.registry.RegistryFactory;
import com.jayhrn.jayrpc.springboot.starter.annotation.JayRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * RPC 服务提供者启动
 *
 * @Author JayHrn
 * @Date 2025/6/26 13:57
 * @Version 1.0
 */
@Slf4j
public class RpcProviderBoostrap implements BeanPostProcessor {

    /**
     * Bean 初始化后执行，注册服务
     * 每个 Bean 初始化后都会调用这个函数如何判断是否包含注解，包含就进行注册服务
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 获取 bean 对象
        Class<?> beanClass = bean.getClass();
        // 判断这个Bean是否包含 @JayRpcService 注解，注解用于服务实现类上，例如 UserServiceImpl 类上
        JayRpcService jayRpcService = beanClass.getAnnotation(JayRpcService.class);
        if (jayRpcService != null) {
            // 需要注册服务
            // 1. 获取服务注册基本信息，如果配置了则直接获取实现的接口
            Class<?> interfaceClass = jayRpcService.interfaceClass();
            // 如果是默认值，则处理
            if (interfaceClass == void.class) {
                // 此时通过 beanClass.getInterfaces()[0] 获取到 UserService.class，如果该注解没有配置 interfaceClass 这个属性
                interfaceClass = beanClass.getInterfaces()[0];
            }
            // 服务名称，全限定名
            String serviceName = interfaceClass.getName();
            String serviceVersion = jayRpcService.serviceVersion();
            // 2. 注册服务
            // 本地注册
            LocalRegistry.register(serviceName, beanClass);

            // 全局配置，默认优先走配置文件，其次类默认配置
            final RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            // 注册服务到注册中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(serviceVersion);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
            try {
                registry.register(serviceMetaInfo);
            } catch (Exception e) {
                throw new RuntimeException(serviceName + " 服务注册失败", e);
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}

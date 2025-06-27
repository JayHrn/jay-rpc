package com.jayhrn.jayrpc.springboot.starter.bootstrap;

import com.jayhrn.jayrpc.proxy.ServiceProxyFactory;
import com.jayhrn.jayrpc.springboot.starter.annotation.JayRpcReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

/**
 * RPC 服务消费者启动
 *
 * @Author JayHrn
 * @Date 2025/6/26 14:15
 * @Version 1.0
 */
@Slf4j
public class RpcConsumerBootstrap implements BeanPostProcessor {

    /**
     * Bean 初始化后执行，注入服务
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 获取类对象，所有的类都会判断
        Class<?> beanClass = bean.getClass();
        // 遍历对象的所有属性
        Field[] fields = beanClass.getDeclaredFields();
        for (Field field : fields) {
            // 判断每个属性是否包含 @JayRpcReference 注解
            JayRpcReference jayRpcReference = field.getAnnotation(JayRpcReference.class);
            if (jayRpcReference != null) {
                // 为属性生成代理对象，获取注解中指定的接口类型
                Class<?> interfaceClass = jayRpcReference.interfaceClass();
                // 如果注解中没有显式指定接口类型（默认值为 void.class）
                if (interfaceClass == void.class) {
                    // 则使用字段的声明类型作为接口类型
                    interfaceClass = field.getType();
                }
                field.setAccessible(true);
                // 使用工厂生成接口的代理对象，该对象负责远程调用
                Object proxyObject = ServiceProxyFactory.getProxy(interfaceClass);
                try {
                    field.set(bean, proxyObject);
                    field.setAccessible(false);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("为字段注入代理对象失败", e);
                }
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}

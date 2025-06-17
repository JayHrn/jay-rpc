package com.jayhrn.jayrpc.proxy;

import java.lang.reflect.Proxy;

/**
 * @Author JayHrn
 * @Date 2025/6/15 18:24
 * @Version 1.0
 */
public class ServiceProxyFactory {
    public static <T> T getProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }
}
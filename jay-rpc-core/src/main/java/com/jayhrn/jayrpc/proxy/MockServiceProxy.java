package com.jayhrn.jayrpc.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Mock 服务代理（JDK 动态代理）
 *
 * @Author JayHrn
 * @Date 2025/6/17 15:54
 * @Version 1.0
 */
@Slf4j
public class MockServiceProxy implements InvocationHandler {
    /**
     * 调用代理
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 根据方法的返回值类型，生成特定的默认对象
        Class<?> methodReturnType = method.getReturnType();
        log.info("mock invoke {}", method.getName());
        return getDefaultObject(methodReturnType);
    }

    /**
     * 生成指定类型的默认对象
     *
     * @param methodReturnType 方法返回类型
     * @return 对象
     */
    private Object getDefaultObject(Class<?> methodReturnType) {
        // 基本类型
        if (methodReturnType.isPrimitive()) {
            if (methodReturnType == boolean.class) {
                return false;
            } else if (methodReturnType == short.class) {
                return (short) 0;
            } else if (methodReturnType == int.class) {
                return (char) 0;
            } else if (methodReturnType == long.class) {
                return 0L;
            }
        }
        // 对象类型
        return null;
    }
}

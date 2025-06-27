package com.jayhrn.jayrpc.springboot.starter.annotation;

import com.jayhrn.jayrpc.springboot.starter.bootstrap.RpcConsumerBootstrap;
import com.jayhrn.jayrpc.springboot.starter.bootstrap.RpcInitBootstrap;
import com.jayhrn.jayrpc.springboot.starter.bootstrap.RpcProviderBoostrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用 RPC 注解
 *
 * @Author JayHrn
 * @Date 2025/6/26 13:19
 * @Version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBoostrap.class, RpcConsumerBootstrap.class})
public @interface EnableJayRpc {

    /**
     * 是否需要启动 server
     *
     * @return
     */
    boolean needServer() default true;
}

package com.jayhrn.jayrpc.bootstrap;

import com.jayhrn.jayrpc.RpcApplication;

/**
 * 服务消费者启动类（初始化）
 *
 * @Author JayHrn
 * @Date 2025/6/25 20:36
 * @Version 1.0
 */
public class ConsumerBoostrap {

    /**
     * 初始化
     */
    public static void init() {

        // RPC 框架初始化（配置和注册中心）
        RpcApplication.init();
    }
}
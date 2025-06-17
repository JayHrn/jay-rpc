package com.jayhrn.example.consumer;

import com.jayhrn.jayrpc.config.RpcConfig;
import com.jayhrn.jayrpc.utils.ConfigUtils;

/**
 * 服务消费者示例
 *
 * @Author JayHrn
 * @Date 2025/6/17 14:54
 * @Version 1.0
 */
public class Consumer {
    public static void main(String[] args) {
        RpcConfig rpcConfig = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpcConfig);
    }
}

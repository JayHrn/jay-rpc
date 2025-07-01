package com.jayhrn.jayrpc.jayrpcspringbootprovider;

import com.jayhrn.jayrpc.springboot.starter.annotation.EnableJayRpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 示例 Spring Boot 服务提供者应用
 *
 * @Author JayHrn
 * @Date 2025/7/1 13:45
 * @Version 1.0
 */
@SpringBootApplication
@EnableJayRpc
public class JayRpcSpringbootProviderApplication {

    public static void main(String[] args) {

        SpringApplication.run(JayRpcSpringbootProviderApplication.class, args);
    }

}

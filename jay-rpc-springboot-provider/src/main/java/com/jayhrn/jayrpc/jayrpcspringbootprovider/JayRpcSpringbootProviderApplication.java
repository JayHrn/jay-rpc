package com.jayhrn.jayrpc.jayrpcspringbootprovider;

import com.jayhrn.jayrpc.springboot.starter.annotation.EnableJayRpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJayRpc
public class JayRpcSpringbootProviderApplication {

    public static void main(String[] args) {

        SpringApplication.run(JayRpcSpringbootProviderApplication.class, args);
    }

}

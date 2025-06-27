package com.jayhrn.jayrpc.jayrpcspringbootconsumer;

import com.jayhrn.jayrpc.springboot.starter.annotation.EnableJayRpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJayRpc(needServer = false) //表示是消费端，不需要启动 server
public class JayRpcSpringbootConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JayRpcSpringbootConsumerApplication.class, args);
    }

}

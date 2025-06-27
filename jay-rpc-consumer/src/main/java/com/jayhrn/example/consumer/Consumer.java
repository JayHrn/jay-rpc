package com.jayhrn.example.consumer;

import com.jayhrn.example.model.User;
import com.jayhrn.example.service.UserService;
import com.jayhrn.jayrpc.bootstrap.ConsumerBoostrap;
import com.jayhrn.jayrpc.proxy.ServiceProxyFactory;

/**
 * 服务消费者示例
 *
 * @Author JayHrn
 * @Date 2025/6/17 14:54
 * @Version 1.0
 */
public class Consumer {
    public static void main(String[] args) {
        // 服务消费者初始化
        ConsumerBoostrap.init();
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("JayHrn");
        // 调用
        for (int i = 0; i < 3; i++) {
            User newUser = userService.getUser(user);
            if (newUser != null) {
                System.out.println(newUser.getName());
            } else {
                System.out.println("user == null");
            }
        }

        // 测试Mock输出，如果number=0说明调用了MockServiceProxy模拟服务代理，如果为1调用的默认实现的真实服务
//        long number = userService.getNumber();
//        System.out.println(number);
    }
}

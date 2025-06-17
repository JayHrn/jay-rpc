package com.jayhrn.example.consumer;

import com.jayhrn.example.model.User;
import com.jayhrn.example.service.UserService;
import com.jayhrn.jayrpc.proxy.ServiceProxyFactory;

/**
 * 服务消费者
 *
 * @Author JayHrn
 * @Date 2025/6/15 16:35
 * @Version 1.0
 */
public class EasyConsumer {
    public static void main(String[] args) {
        // 静态代理
//        UserService userService = new UserServiceProxy();
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("JayHrn");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}

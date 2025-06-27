package com.jayhrn.jayrpc.jayrpcspringbootprovider;

import com.jayhrn.example.model.User;
import com.jayhrn.example.service.UserService;
import com.jayhrn.jayrpc.springboot.starter.annotation.JayRpcService;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 *
 * @Author JayHrn
 * @Date 2025/6/26 14:47
 * @Version 1.0
 */
@Service
@JayRpcService
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名: " + user.getName());
        return user;
    }
}
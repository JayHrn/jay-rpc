package com.jayhrn.example.provider;

import com.jayhrn.example.model.User;
import com.jayhrn.example.service.UserService;

/**
 * @Author JayHrn
 * @Date 2025/6/15 16:32
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名: "+ user.getName());
        return user;
    }
}

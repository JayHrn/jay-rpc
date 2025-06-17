package com.jayhrn.example.service;

import com.jayhrn.example.model.User;

/**
 * 用户服务
 *
 * @Author JayHrn
 * @Date 2025/6/15 16:11
 * @Version 1.0
 */
public interface UserService {
    /**
     * 获取用户
     *
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * 用户测试 mock 接口返回值
     *
     * @return
     */
    default short getNumber() {
        return 1;
    }
}

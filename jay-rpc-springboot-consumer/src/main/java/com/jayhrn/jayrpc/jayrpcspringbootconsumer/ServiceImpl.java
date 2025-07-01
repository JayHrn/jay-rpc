package com.jayhrn.jayrpc.jayrpcspringbootconsumer;

import com.jayhrn.example.model.User;
import com.jayhrn.example.service.UserService;
import com.jayhrn.jayrpc.springboot.starter.annotation.JayRpcReference;
import org.springframework.stereotype.Service;

/**
 * 示例服务实现类
 *
 * @Author JayHrn
 * @Date 2025/6/26 14:50
 * @Version 1.0
 */
@Service
public class ServiceImpl {

    /**
     * 使用 RPC 框架注入
     */
    @JayRpcReference
    private UserService userService;

    /**
     * 测试方法
     */
    public void test() {
        User user = new User();
        user.setName("JayHrn");
        User resultUser = userService.getUser(user);
        System.out.println(resultUser.getName());
    }
}

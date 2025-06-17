package com.jayhrn.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.jayhrn.example.model.User;
import com.jayhrn.example.service.UserService;
import com.jayhrn.jayrpc.model.RpcRequest;
import com.jayhrn.jayrpc.model.RpcResponse;
import com.jayhrn.jayrpc.serializer.JdkSerializer;
import com.jayhrn.jayrpc.serializer.Serializer;

/**
 * 用户服务静态代理
 *
 * @Author JayHrn
 * @Date 2025/6/15 17:52
 * @Version 1.0
 */
public class UserServiceProxy implements UserService {
    @Override
    public User getUser(User user) {
        // 制定序列化器
        final Serializer serializer = new JdkSerializer();

        // 构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                // 方法名称
                .methodName("getUser")
                // 参数类型User类型
                .parameterTypes(new Class[]{User.class})
                // 参数就是user
                .args(new Object[]{user})
                .build();
        try {
            // 序列化 (Java对象 => 字节数组)
            byte[] bodyBytes = serializer.serialize(rpcRequest);

            // 发送请求
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                // 获取响应
                byte[] result = httpResponse.bodyBytes();
                // 反序列化 (字节数组 => Java对象)
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return (User) rpcResponse.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

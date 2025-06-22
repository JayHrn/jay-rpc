package com.jayhrn.jayrpc.proxy;

import cn.hutool.core.collection.CollUtil;
import com.jayhrn.jayrpc.RpcApplication;
import com.jayhrn.jayrpc.config.RpcConfig;
import com.jayhrn.jayrpc.constant.RpcConstant;
import com.jayhrn.jayrpc.model.RpcRequest;
import com.jayhrn.jayrpc.model.RpcResponse;
import com.jayhrn.jayrpc.model.ServiceMetaInfo;
import com.jayhrn.jayrpc.registry.Registry;
import com.jayhrn.jayrpc.registry.RegistryFactory;
import com.jayhrn.jayrpc.serializer.Serializer;
import com.jayhrn.jayrpc.serializer.SerializerFactory;
import com.jayhrn.jayrpc.server.tcp.VertxTcpClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 服务代理（JDK 动态代理）
 *
 * @Author JayHrn
 * @Date 2025/6/15 18:27
 * @Version 1.0
 */
public class ServiceProxy implements InvocationHandler {
    /**
     * 调用代理
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        String serviceName = method.getDeclaringClass().getName();

        // 构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 序列化，这里不需要对 rpcRequest 进行序列化
//            byte[] bodyBytes = serializer.serialize(rpcRequest);

            // 从注册中心获取服务提供者请求地址
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            // 获取制定类别的注册中心
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
            if (CollUtil.isEmpty(serviceMetaInfoList)) {
                throw new RuntimeException("暂无服务地址");
            }

            // 暂时先取第一个
            ServiceMetaInfo selectedServiceMetaInfo = serviceMetaInfoList.get(0);

            // 发送 TCP 请求，使用 VertxTcpClient 封装了请求过程
            RpcResponse rpcResponse = VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo);

            return rpcResponse.getData();

//            // 更改为发送 TCP 请求
//            Vertx vertx = Vertx.vertx();
//            NetClient netClient = vertx.createNetClient();
//            CompletableFuture<RpcResponse> responseCompletableFuture = new CompletableFuture<>();
//            netClient.connect(selectedServiceMetaInfo.getServicePort(), selectedServiceMetaInfo.getServiceHost(), result -> {
//                if (result.succeeded()) {
//                    System.out.println("Connected to TCP Server");
//                    NetSocket netSocket = result.result();
//                    // 发送数据
//                    // 构造消息
//                    ProtocolMessage<RpcRequest> protocolMessage = new ProtocolMessage<>();
//                    ProtocolMessage.Header header = new ProtocolMessage.Header();
//                    header.setMagic(ProtocolConstant.PROTOCOL_MAGIC);
//                    header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
//                    header.setSerializer((byte) ProtocolMessageSerializerEnum.getEnumByValue(RpcApplication.getRpcConfig().getSerializer()).getKey());
//                    header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
//                    header.setRequestId(IdUtil.getSnowflakeNextId());
//                    protocolMessage.setHeader(header);
//                    protocolMessage.setBody(rpcRequest);
//                    // 编码请求
//                    try {
//                        Buffer encode = ProtocolMessageEncoder.encode(protocolMessage);
//                        netSocket.write(encode);
//                    } catch (IOException e) {
//                        throw new RuntimeException("协议消息编码错误");
//                    }
//
//                    // 接收响应
//                    netSocket.handler(buffer -> {
//                        try {
//                            ProtocolMessage<RpcResponse> rpcResponseProtocolMessage = (ProtocolMessage<RpcResponse>) ProtocolMessageDecoder.decode(buffer);
//                            responseCompletableFuture.complete(rpcResponseProtocolMessage.getBody());
//                        } catch (IOException e) {
//                            throw new RuntimeException("协议消息解码错误");
//                        }
//                    });
//                } else {
//                    System.err.println("Failed to connect to TCP Server");
//                }
//            });
//            RpcResponse rpcResponse = responseCompletableFuture.get();
//            // 关闭连接
//            netClient.close();
//            return rpcResponse.getData();


            // 发送请求，如下的内容为发送 HTTP 请求，已不使用，将在下一次 commit 时删除
            // TODO: 注意，这里地址被硬编码了（需要使用注册中心和服务发现机制解决）
            // 2025年6月19日配置 已解决
//            try (HttpResponse httpResponse = HttpRequest.post(selectedServiceMetaInfo.getServiceAddress())
//                    .body(bodyBytes)
//                    .execute()) {
//                // 接受响应
//                byte[] result = httpResponse.bodyBytes();
//                // 反序列化
//                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
//                return rpcResponse.getData();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.jayhrn.jayrpc.server.tcp;

import cn.hutool.core.util.IdUtil;
import com.jayhrn.jayrpc.RpcApplication;
import com.jayhrn.jayrpc.model.RpcRequest;
import com.jayhrn.jayrpc.model.RpcResponse;
import com.jayhrn.jayrpc.model.ServiceMetaInfo;
import com.jayhrn.jayrpc.protocol.*;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Vertx TCP 请求客户端
 *
 * @Author JayHrn
 * @Date 2025/6/21 18:42
 * @Version 1.0
 */
public class VertxTcpClient {

    /**
     * 发送请求
     *
     * @param request
     * @param serviceMetaInfo
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static RpcResponse doRequest(RpcRequest request, ServiceMetaInfo serviceMetaInfo) throws ExecutionException, InterruptedException {
        // 发送 TCP 请求
        Vertx vertx = Vertx.vertx();
        NetClient netClient = vertx.createNetClient();
        CompletableFuture<RpcResponse> responseCompletableFuture = new CompletableFuture<>();
        netClient.connect(serviceMetaInfo.getServicePort(), serviceMetaInfo.getServiceHost(), result -> {
            if (!result.succeeded()) {
                System.out.println("Failed to connect to TCP server");
                responseCompletableFuture.completeExceptionally(result.cause()); // 抛出异常，便于重试机制捕获，非常关键！
                return;
            }
            NetSocket netSocket = result.result();
            // 发送请求
            // 构造消息
            ProtocolMessage<RpcRequest> protocolMessage = new ProtocolMessage<>();
            ProtocolMessage.Header header = new ProtocolMessage.Header();
            header.setMagic(ProtocolConstant.PROTOCOL_MAGIC);
            header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
            header.setSerializer((byte) ProtocolMessageSerializerEnum.getEnumByValue(RpcApplication.getRpcConfig().getSerializer()).getKey());
            header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
            // 生成全局请求 ID
            header.setRequestId(IdUtil.getSnowflakeNextId());
            protocolMessage.setHeader(header);
            protocolMessage.setBody(request);

            // 编码请求
            try {
                Buffer encodeBuffer = ProtocolMessageEncoder.encode(protocolMessage);
                netSocket.write(encodeBuffer);
            } catch (IOException e) {
                throw new RuntimeException("协议消息编码错误");
            }

            // 接收响应
            TcpBufferHandlerWrapper tcpBufferHandlerWrapper = new TcpBufferHandlerWrapper(buffer -> {
                try {
                    ProtocolMessage<RpcResponse> rpcResponseProtocolMessage = (ProtocolMessage<RpcResponse>) ProtocolMessageDecoder.decode(buffer);
                    responseCompletableFuture.complete(rpcResponseProtocolMessage.getBody());
                } catch (IOException e) {
                    throw new RuntimeException("协议信息解码错误");
                }
            });
            netSocket.handler(tcpBufferHandlerWrapper);
        });

        RpcResponse rpcResponse = responseCompletableFuture.get();
        // 关闭连接
        netClient.close();
        return rpcResponse;
    }

    public void start(int serverPort) {

        // 创建 Vertx 实例
        Vertx vertx = Vertx.vertx();

        vertx.createNetClient().connect(serverPort, "localhost", result -> {
            if (result.succeeded()) {

                System.out.println("Connected to TCP server");
                NetSocket socket = result.result();

                // 发送数据
//                socket.write("Hello, Server!");

                // 测试半包粘包现象
                for (int i = 0; i < 1000; i++) {
//                    socket.write("Hello, Server! Hello, Server! Hello, Server! Hello, Server!");
                    // 发送数据
                    Buffer buffer = Buffer.buffer();
                    String str = "Hello, Server! Hello, Server! Hello, Server! Hello, Server!";

                    buffer.appendInt(0); // 4个字节
                    buffer.appendInt(str.getBytes().length); // 4个字节，存储着字符串长度
                    buffer.appendBytes(str.getBytes()); // 字符串内容，可变
                    socket.write(buffer);
                }

                socket.handler(buffer -> {
                    System.out.println("Received response from server: " + buffer.toString());
                });
            } else {
                System.out.println("Failed to connect to TCP server");
            }
        });
    }

    public static void main(String[] args) {
        VertxTcpClient client = new VertxTcpClient();
        client.start(8080);
    }
}

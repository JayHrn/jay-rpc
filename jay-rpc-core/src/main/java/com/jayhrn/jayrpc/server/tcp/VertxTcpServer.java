package com.jayhrn.jayrpc.server.tcp;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import lombok.extern.slf4j.Slf4j;

/**
 * Vertx TCP 服务器
 *
 * @Author JayHrn
 * @Date 2025/6/21 18:29
 * @Version 1.0
 */
@Slf4j
public class VertxTcpServer {
    private byte[] handleRequest(byte[] requestData) {
        return "Hello, client!".getBytes();
    }

    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 TCP 服务器
        NetServer server = vertx.createNetServer();

        // 处理请求
        server.connectHandler(new TcpServerHandler());

//        server.connectHandler(socket -> {
//
//            // 处理链接
////            socket.handler(buffer -> {
//
////                System.out.println("Received response from client: " + buffer.toString());
//
////            String testMessage = "Hello, Server! Hello, Server! Hello, Server! Hello, Server!";
////            int messageLength = testMessage.getBytes().length;
//
//            // 构造 parser
//            RecordParser recordParser = RecordParser.newFixed(8);
//            recordParser.setOutput(new Handler<Buffer>() {
//
//                // 初始化
//                int size = -1;
//                // 一次完整的读取 ( 头 + 体 )
//                Buffer resultBuffer = Buffer.buffer();
//
//                @Override
//                public void handle(Buffer buffer) {
//                    if (size == -1) {
//                        // 读取消息体长度
//                        size = buffer.getInt(4); // 读取第4字节开始的int值，也就是字符串长度
//                        recordParser.fixedSizeMode(size); // 切换为读取字符串长度的字节的消息体
//
//                        // 写入头信息到结果
//                        resultBuffer.appendBuffer(buffer);
//                    } else {
//                        // 写入体信息到结果
//                        resultBuffer.appendBuffer(buffer); // 从网络接收到后续 字符串长度的 字节
//                        System.out.println(resultBuffer.toString());
//
//                        // 重置一轮
//                        recordParser.fixedSizeMode(8);
//                        size = -1;
//                        resultBuffer = Buffer.buffer();
//                    }
//                }
////                if (buffer.getBytes().length < messageLength) {
////                    System.out.println("半包, length: " + buffer.getBytes().length);
////                    return;
////                }
////
////                if (buffer.getBytes().length > messageLength) {
////                    System.out.println("粘包, length: " + buffer.getBytes().length);
////                }
////
////                String str = new String(buffer.getBytes(0, messageLength));
////                System.out.println(str);
////
////                if (testMessage.equals(str)) {
////                    System.out.println("Good");
////                }
//
////                // 处理接收到的字节数组
////                byte[] requestData = buffer.getBytes();
////
////                // 在这里自定义字节数组处理逻辑，比如解析请求，调用服务，构造响应等
////                byte[] responseData = handleRequest(requestData);
////
////                // 发送请求
////                socket.write(Buffer.buffer(responseData));
//            });
//            socket.handler(recordParser);
//        });

        // 启动 TCP 服务并监听指定端口
        server.listen(port).onSuccess(response -> {
                    log.info("Server is listening on port " + port);
                })
                .onFailure(response -> {
                    log.info("Failed to start server" + response.getCause());
                });
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8080);
    }
}

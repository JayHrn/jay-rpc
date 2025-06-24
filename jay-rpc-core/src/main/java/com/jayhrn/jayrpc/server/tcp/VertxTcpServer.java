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

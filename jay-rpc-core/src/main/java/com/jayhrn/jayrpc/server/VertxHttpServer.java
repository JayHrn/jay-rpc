package com.jayhrn.jayrpc.server;

import io.vertx.core.Vertx;

/**
 * Vertx HTTP 服务器
 *
 * @Author JayHrn
 * @Date 2025/6/15 16:44
 * @Version 1.0
 */
public class VertxHttpServer implements HttpServer {
    /**
     * 启动服务器
     *
     * @param port 端口
     */
    @Override
    public void doStart(int port) {
        // 穿件Vertx实例
        Vertx vertx = Vertx.vertx();

        // 创建HTTP服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        // 监听端口并处理请求
        // 使用自定义的处理器
        server.requestHandler(new HttpServiceHandler());

        // 启动HTTP服务器并监听端口请求
        server.listen(port)
                .onSuccess(response -> {
                    System.out.println("Server is listening on port " + port);
                })
                .onFailure(response -> {
                    System.out.println("Failed to start server on port " + port);
                });
    }
}

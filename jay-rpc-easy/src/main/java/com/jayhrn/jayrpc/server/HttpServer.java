package com.jayhrn.jayrpc.server;

/**
 * HTTP 服务器接口
 *
 * @Author JayHrn
 * @Date 2025/6/15 16:43
 * @Version 1.0
 */
public interface HttpServer {
    /**
     * 启动服务器
     *
     * @param port 端口
     */
    void doStart(int port);
}

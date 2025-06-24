package com.jayhrn.example.model;

import java.io.Serializable;

/**
 * 用户
 * 后续需要进行网络传输需要实现Serializable
 *
 * @Author JayHrn
 * @Date 2025/6/15 16:07
 * @Version 1.0
 */
public class User implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

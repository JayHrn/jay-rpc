package com.jayhrn.jayrpc.jayrpcspringbootconsumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 单元测试
 *
 * @Author JayHrn
 * @Date 2025/6/26 14:55
 * @Version 1.0
 */
@SpringBootTest
public class ServiceImplTest {
    @Autowired
    private ServiceImpl serviceImpl;

    @Test
    public void test() {
        serviceImpl.test();
    }
}

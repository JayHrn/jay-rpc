package com.jayhrn.jayrpc.protocol;

import lombok.Getter;

/**
 * 协议消息的类型枚举
 *
 * @Author JayHrn
 * @Date 2025/6/21 18:06
 * @Version 1.0
 */
@Getter
public enum ProtocolMessageTypeEnum {
    REQUEST(0),
    RESPONSE(1),
    HEART_BEAT(2),
    OTHERS(3);

    private final int key;

    ProtocolMessageTypeEnum(int key) {
        this.key = key;
    }

    /**
     * 根据 key 获取枚举
     *
     * @param key
     * @return
     */
    public static ProtocolMessageTypeEnum getEnumByKey(int key) {
        for (ProtocolMessageTypeEnum protocolMessageTypeEnum : values()) {
            if (key == protocolMessageTypeEnum.key) {
                return protocolMessageTypeEnum;
            }
        }
        return null;
    }
}

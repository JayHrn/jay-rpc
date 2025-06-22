package com.jayhrn.jayrpc.protocol;

import cn.hutool.core.util.IdUtil;
import com.jayhrn.jayrpc.constant.RpcConstant;
import com.jayhrn.jayrpc.model.RpcRequest;
import io.vertx.core.buffer.Buffer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * 自定义协议消息测试
 *
 * @Author JayHrn
 * @Date 2025/6/22 13:54
 * @Version 1.0
 */
public class ProtocolMessageTest {
    @Test
    public void testEncodeAndDecode() throws IOException {
        ProtocolMessage<RpcRequest> protocolMessage = new ProtocolMessage<>();
        ProtocolMessage.Header header = new ProtocolMessage.Header();
        header.setMagic(ProtocolConstant.PROTOCOL_MAGIC);
        header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
        header.setSerializer((byte) ProtocolMessageSerializerEnum.JDK.getKey());
        header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
        header.setStatus((byte) ProtocolMessageStatusEnum.OK.getValue());
        header.setRequestId(IdUtil.getSnowflakeNextId());
        header.setBodyLength(0);
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setServiceName("myService");
        rpcRequest.setMethodName("myMethod");
        rpcRequest.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
        rpcRequest.setParameterTypes(new Class[]{String.class});
        rpcRequest.setArgs(new Object[]{"arg0", "arg1"});
        protocolMessage.setHeader(header);
        protocolMessage.setBody(rpcRequest);

        // 编码
        Buffer encodeBuffer = ProtocolMessageEncoder.encode(protocolMessage);
        // 解码
        ProtocolMessage<?> message = ProtocolMessageDecoder.decode(encodeBuffer);
        Assert.assertNotNull(message);
        System.out.println(message);
    }
}

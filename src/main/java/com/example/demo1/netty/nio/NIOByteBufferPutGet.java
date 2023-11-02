package com.example.demo1.netty.nio;

import java.nio.ByteBuffer;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/20 15:38
 * @version: 1.0
 */
public class NIOByteBufferPutGet {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.putInt(100);
        buffer.putLong(9);
        buffer.putChar('刘');
    }
}

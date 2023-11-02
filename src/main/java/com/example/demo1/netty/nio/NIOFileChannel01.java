package com.example.demo1.netty.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/16 17:03
 * @version: 1.0
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws Exception{
        String  str = "HELLO,你好sda";
        FileOutputStream fileOutputStream = new FileOutputStream("e:\\file01.txt");
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes(StandardCharsets.UTF_8));
        byteBuffer.flip();
        channel.write(byteBuffer);
    }
}

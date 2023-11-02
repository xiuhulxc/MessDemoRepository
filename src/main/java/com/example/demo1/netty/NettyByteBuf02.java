package com.example.demo1.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/11/1 16:03
 * @version: 1.0
 */
public class NettyByteBuf02 {

    public static void main(String[] args) {
        ByteBuf buf = Unpooled.copiedBuffer("hello word!", StandardCharsets.UTF_8);
        if (buf.hasArray()){
            byte[] content = buf.array();
            System.out.println(new String(content,StandardCharsets.UTF_8));

            System.out.println("byteBuf=" + buf);

            System.out.println(buf.arrayOffset()); // 0
            System.out.println(buf.readerIndex()); // 0
            System.out.println(buf.writerIndex()); // 12
            System.out.println(buf.capacity()); // 36
            //System.out.println(byteBuf.readByte()); //
            System.out.println(buf.getByte(0)); // 104
            int i = buf.readableBytes();
            System.out.println("len =" + i);
            for (int j = 0; j < i; j++) {
                System.out.println((char)buf.getByte(j));
            }
            System.out.println(buf.getCharSequence(0, 4, Charset.forName("utf-8")));
            System.out.println(buf.getCharSequence(4, 6, Charset.forName("utf-8")));
        }
    }
}

package com.example.demo1.netty.nio;

import io.netty.buffer.ByteBuf;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/20 15:55
 * @version: 1.0
 * scattering:将数据写入 到buffer时,可以才有buffer数组,依次写入(分散)
 * Gathering:从 buffer读取 数据时,可以 采用buffer数组,依次读
 */
public class ScatteringAndGatheringTest {

    public static void main(String[] args) throws Exception {
        //使用ServerSocketChannel和SocketChannel网络
        ServerSocketChannel open = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        //绑定 端口到socket,并启动
        open.socket().bind(inetSocketAddress);
        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
        //等待客户端连接
        SocketChannel accept = open.accept();
        //循环读取
        while (true) {
            int byteRead = 0;
            while (byteRead < 100) {
                long read = accept.read(byteBuffers);
                byteRead += 1;
                System.out.println("byteRead:" + byteRead);
                Arrays.stream(byteBuffers).map(buffer ->
                        "position=" + buffer.position() + "limit:" + buffer.limit()
                ).forEach(System.out::println);
                Arrays.asList(byteBuffers).forEach(Buffer::flip);
                //将数据读出显示到 客户端
                long byteWirte = 0;
                while (byteWirte < 100) {
                    long write = accept.write(byteBuffers);
                    byteWirte += write;
                }
                Arrays.asList(byteBuffers).forEach(Buffer::clear);
                System.out.println("end");
            }
        }
    }
}

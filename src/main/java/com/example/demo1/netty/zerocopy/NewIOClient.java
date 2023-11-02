package com.example.demo1.netty.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/21 16:22
 * @version: 1.0
 */
public class NewIOClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 7001));
        String fileName = "protoc-3.6.1-win32.zip";
        FileChannel channel = new FileInputStream(fileName).getChannel();
        long startTime = System.currentTimeMillis();
        long transferTo = channel.transferTo(0, channel.size(), socketChannel);
        System.out.println("发送的总字节数:" + transferTo + "耗时:" + (System.currentTimeMillis() - startTime));
        channel.close();
    }
}

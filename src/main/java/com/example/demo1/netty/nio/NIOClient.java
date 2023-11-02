package com.example.demo1.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/20 21:00
 * @version: 1.0
 */
public class NIOClient {

    public static void main(String[] args) throws Exception {
       //得到一个 网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞
        socketChannel.configureBlocking(false);
        //提供服务 端的ip和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        //连接服务器
        if (!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("连接需要时间,客户端不会阻塞 ,可以做其他工作");
            }
        }
        //如果连接成功,就发送 数据
        String  str = "hello,尚硅谷";
        ByteBuffer wrap = ByteBuffer.wrap(str.getBytes());
        //发送数据 ,将 buffer写入到channel
        socketChannel.write(wrap);
        System.out.println("ok");

    }
}

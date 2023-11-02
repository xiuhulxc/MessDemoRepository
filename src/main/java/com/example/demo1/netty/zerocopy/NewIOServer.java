package com.example.demo1.netty.zerocopy;

import io.netty.buffer.ByteBuf;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/21 16:09
 * @version: 1.0
 */
public class NewIOServer {
    public static void main(String[] args) throws Exception{
        InetSocketAddress socketAddress = new InetSocketAddress(7001);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(socketAddress);
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            int  readCount = 0;
            while (-1  != readCount){
                try {
                    readCount = socketChannel.read(byteBuffer);
                }catch (Exception e){
                    e.printStackTrace();
                }
                byteBuffer.rewind();//倒带position=0
            }
        }
    }
}

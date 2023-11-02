package com.example.demo1.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/20 20:20
 * @version: 1.0
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        //服务端,创建ServerSocketChannel -> ServerSocket
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        //创建selector对象
        Selector selector = Selector.open();
        //绑定端口6666
        socketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        socketChannel.configureBlocking(false);
        //把serverSocketChannel注册 到selector关心连接事件为OP_ACCEPT
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //注册等待客户端连接
        while (true) {
            if (selector.select(1000) == 0) {//没有事件 发生
                System.out.println("服务端等待1s,无连接");
                continue;
            }
            //>0则获取 到selector集合
            //通过selectionKeys反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                //根据key获取到对应的通道发生的 事件 做事件处理
                if (selectionKey.isAcceptable()) {//如果是OP_ACCEPT说明有一个客户端来连接
                    //给该客户端生成一个socketChannel
                    SocketChannel channel = socketChannel.accept();
                    System.out.println("客户端连接成功,生成一个socketChannel:" + channel.hashCode());
                    channel.configureBlocking(false);
                    //将SocketChannel注册到selector上去
                    channel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (selectionKey.isReadable()) {//读取事件
                    //通过key反向获取到channel
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    //获取到该 channel关联的buffer
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    channel.read(byteBuffer);
                    System.out.println("from客户端 数据:" + new String(byteBuffer.array()));
                }
                //手动从集合 中移除 当前的selectionkey,防止重复操作
                iterator.remove();
            }
        }
    }
}

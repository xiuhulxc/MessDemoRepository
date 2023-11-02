package com.example.demo1.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/20 21:53
 * @version: 1.0
 */
public class GroupChatServer {

    private Selector selector;
    private ServerSocketChannel listenChannel;//用于监听
    private static final int PORT = 6667;

    //构造器
    //初始化工作
    public GroupChatServer() {
        try {
            //得到选择器
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            listenChannel.configureBlocking(false);
            //将channel注册到Selector
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    //监听
    public void listen() {
        try {
            //循环
            while (true) {
                int select = selector.select(2000);
                if (select > 0) {//说明有事件处理
                    //遍历得到 的seletorKey
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        //取出selectionKey
                        SelectionKey selectionKey = iterator.next();
                        //判断是什么事件
                        if (selectionKey.isAcceptable()) {
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            //将sc注册到selector
                            sc.register(selector, SelectionKey.OP_READ);
                            //提示
                            System.out.println(sc.getRemoteAddress() + "上线");
                        }
                        if (selectionKey.isReadable()) { //处理读事件
                            readDate(selectionKey);
                        }
                        iterator.remove();
                    }
                } else {
//                    System.out.println("等待...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    //读取客户端消息
    private void readDate(SelectionKey key) {
        //定义一个SocketChannel
        SocketChannel channel = null;
        try {
            //取到关联 的channel
            channel = (SocketChannel) key.channel();
            //创建buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int read = channel.read(byteBuffer);
            if (read > 0) {
                String message = new String(byteBuffer.array());
                System.out.println("from客户端" + message);
                //向其他客户端转发消息,
                sendInfoToOtherClients(message, channel);
            }
        } catch (Exception e) {
            try {
                System.out.println(channel.getRemoteAddress() + "离线 ");
                //取消注册
                key.cancel();
                channel.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    //转发消息 给其他客户(通道)
    private void sendInfoToOtherClients(String msg, SocketChannel socketChannel) throws IOException {
        System.out.println("转发消息");
        for (SelectionKey key : selector.keys()) {
            //通过key取出对应的socketChannel
            Channel targetChannel = key.channel();
            //排除自己
            if (targetChannel instanceof SocketChannel && targetChannel != socketChannel) {
                //转发
                SocketChannel dest = (SocketChannel) targetChannel;
                ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
                //将buffer的数据写入到通道
                dest.write(wrap);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}

package com.example.demo1.netty.bio;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @descriptions: nio练习
 * @author: Liu XuChao
 * @date: 2023/8/16 15:06
 * @version: 1.0
 */
public class BIODemo {

    public static void main(String[] args) throws Exception {
        /**
         * 1.创建一个线程池
         * 2.如果有客户端连接,创建一个线程,与之通讯
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(7777);
        System.out.println("服务端启动");
        while (true) {
            //监听,等待客户端连接
            System.out.println("等待连接...");
            final Socket accept = serverSocket.accept();
            System.out.println("连接成功");
            //创建一个线程,通信
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    handler(accept);
                }
            });
        }
    }

    public static void handler(Socket socket) {
        try {
//            System.out.println("线程id:" + Thread.currentThread().getId() + "name:" + Thread.currentThread().getName());
            //通过socket获取输入 流
            InputStream inputStream = socket.getInputStream();
            byte[] by = new byte[1024];
            //循环读取客户端发送的数据
            while (true) {
//                System.out.println("线程id:" + Thread.currentThread().getId() + "name:" + Thread.currentThread().getName());
                System.out.println("reading...");
                int read = inputStream.read(by);
                if (read != -1) {
                    System.out.println(new String(by, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client连接");
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

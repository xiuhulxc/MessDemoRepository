package com.example.demo1.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/20 15:32
 * @version: 1.0
 */
public class NIOFileChannel04 {

    //文件拷贝
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("f:\\100.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("f:\\101.jpg");
        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();
        outputStreamChannel.transferFrom(inputStreamChannel,0,inputStreamChannel.size());
        inputStreamChannel.close();
        outputStreamChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}

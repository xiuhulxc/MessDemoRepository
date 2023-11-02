package com.example.demo1.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/20 15:17
 * @version: 1.0
 */
public class NIOFileChannel03 {

    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel channel = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel channel1 = fileOutputStream.getChannel();
        ByteBuffer allocate = ByteBuffer.allocate(512);
        while (true){ //循环读取
            allocate.clear();
            int read = channel.read(allocate);
            if (read == -1){
                break;
            }
            // 将buffer 中的数据写入到channel1
            allocate.flip();
            channel1.write(allocate);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}

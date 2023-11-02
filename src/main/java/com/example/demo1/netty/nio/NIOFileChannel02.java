package com.example.demo1.netty.nio;

import io.netty.buffer.ByteBuf;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/20 15:06
 * @version: 1.0
 */
public class NIOFileChannel02 {

    public static void main(String[] args) throws Exception{
        //创建文件输出流
        File file = new File("e:\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        //通过fileinputStream获取 对应的 fileChannel->实际类型fileChannelImpl
        FileChannel channel = fileInputStream.getChannel();
        //创建缓存区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        //将通信的读入数据并放入到buffer
        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();

    }
}

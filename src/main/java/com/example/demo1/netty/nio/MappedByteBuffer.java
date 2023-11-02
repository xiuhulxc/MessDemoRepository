package com.example.demo1.netty.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/20 15:42
 * @version: 1.0
 * MappedByteBuffer 可以让文件直接在内存(堆外内存)中修改,操作系统不需要拷贝一次
 */
public class MappedByteBuffer {
    public static void main(String[] args) throws Exception{
        RandomAccessFile rw = new RandomAccessFile("1.txt", "rw");
        FileChannel channel = rw.getChannel();

        /**
         * 1.使用读写模式
         * 2.可以修改的起始位置
         * 3.映射内存的大小
         */
        java.nio.MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(0,(byte) 'H');
        map.put(3,(byte) 'o');
        channel.close();
        rw.close();
    }
}

package com.example.demo1.netty.nio;

import java.nio.IntBuffer;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/16 15:55
 * @version: 1.0
 */
public class BasicBuffer {

    public static void main(String[] args) {
        //创建一个buffer大小 为5,存放5个int类型
        IntBuffer allocate = IntBuffer.allocate(5);
        for (int i = 0; i < allocate.capacity(); i++) {
            allocate.put(i * 2);
        }
        //从buffer读取数据
        //转换buffer,读写切换
        allocate.flip();
        while (allocate.hasRemaining()){
            System.out.println(allocate.get());
        }
    }
}

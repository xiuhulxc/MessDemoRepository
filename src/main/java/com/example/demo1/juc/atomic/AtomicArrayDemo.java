package com.example.demo1.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/30 18:35
 * @version: 1.0
 */
public class AtomicArrayDemo {

    public static void main(String[] args) {
        AtomicIntegerArray array = new AtomicIntegerArray(new int[5]);
        for (int i = 0; i < array.length(); i++) {
            System.out.println(array.get(i));
        }
        int tmpint  = 0;
        tmpint  = array.getAndSet(0,455);
        System.out.println(tmpint+":"+array.get(0));
        tmpint  = array.getAndIncrement(0);
        System.out.println(tmpint+":"+array.get(0));
    }
}

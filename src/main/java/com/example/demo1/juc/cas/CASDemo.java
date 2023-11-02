package com.example.demo1.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/30 14:57
 * @version: 1.0
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5,2022)+":"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,2022)+":"+atomicInteger.get());

    }
}

package com.example.demo1.juc.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/30 18:20
 * @version: 1.0
 */

class MyNumber {
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addPlus() {
        atomicInteger.getAndIncrement();
    }
}

public class AtomicIntegerDemo {

    public static final int size = 50;

    public static void main(String[] args) throws InterruptedException {
        MyNumber myNumber = new MyNumber();
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        myNumber.addPlus();
                    }
                }finally {
                    countDownLatch.countDown();
                }

            }, String.valueOf(i)).start();
        }
        //等待线程全部计算完成 后获取最后的结果
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "result:" + myNumber.atomicInteger.get());

    }
}

package com.example.demo1.juc.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/28 19:24
 * @version: 1.0
 */
public class InterruptDemo {

    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + "atomicBoolean被修改为true,程序 停止");
                    break;
                }
                System.out.println("ti--hello  volatile");
            }
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        new Thread(() -> {
            atomicBoolean.set(true);
        },"t2").start();
    }


}

package com.example.demo1.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/28 16:43
 * @version: 1.0
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        final Object oa = new Object();
        final Object ob = new Object();
        new Thread(() -> {
            synchronized (oa) {
                System.out.println(Thread.currentThread().getName() + "持有a锁,希望获得b");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                synchronized (ob) {
                    System.out.println(Thread.currentThread().getName() + "成功获得b");
                }
            }
        }, "a").start();
        new Thread(() -> {
            synchronized (ob) {
                System.out.println(Thread.currentThread().getName() + "持有b锁,希望获得a");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                synchronized (oa) {
                    System.out.println(Thread.currentThread().getName() + "成功获得a");
                }
            }
        }, "b").start();
    }
}

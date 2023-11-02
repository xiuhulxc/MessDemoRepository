package com.example.demo1.juc.lock;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/28 16:26
 * @version: 1.0
 */
public class ReEntryLockDemo {

    public static void main(String[] args) {

       extracted();

    }

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName() + "come in...");
    }

    private static void extracted() {
        final Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + "外层调用");
                synchronized (o) {
                    System.out.println(Thread.currentThread().getName() + "中层调用");
                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName() + "内层调用");
                    }
                }
            }
        }, "t1").start();
    }
}

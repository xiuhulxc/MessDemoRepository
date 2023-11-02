package com.example.demo1.juc.volatils;

import java.util.concurrent.TimeUnit;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/29 21:31
 * @version: 1.0
 */
public class VolatilsSeeDemo {

    static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"come in");
            while (flag){

            }
            System.out.println(Thread.currentThread().getName()+"被设置为flag,线程终止");
        },"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        flag = true;
        System.out.println(Thread.currentThread().getName()+"修改完成");

    }
}

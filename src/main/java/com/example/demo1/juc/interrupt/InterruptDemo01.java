package com.example.demo1.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/28 19:38
 * @version: 1.0
 */
public class InterruptDemo01 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                System.out.println("----"  + i);
            }
            System.out.println("中断前默认值 :" + Thread.currentThread().isInterrupted());
        },"t1");
        thread.start();
        System.out.println("默认值 :" + thread.isInterrupted());
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        thread.interrupt();
        System.out.println("中断后默认值 :" + thread.isInterrupted());

    }
}

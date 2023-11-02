package com.example.demo1.juc.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/28 19:47
 * @version: 1.0
 */
public class InterruptDemo02 {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName()+":"+Thread.currentThread().isInterrupted()+"程序停止");
               break;
                }
                System.out.println("---hello  interruptDemo03");
            }
        },"t1");
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        Thread thread1 = new Thread(() -> {
            thread.interrupt();
        }, "t2");
        thread1.start();
    }
}

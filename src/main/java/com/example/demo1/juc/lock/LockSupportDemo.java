package com.example.demo1.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/29 15:58
 * @version: 1.0
 */
public class LockSupportDemo {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "被唤醒");
        }, "t1");
        thread.start();
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException interruptedException) {
//            interruptedException.printStackTrace();
//        }
        new Thread(() -> {
            try {
                LockSupport.unpark(thread);
                System.out.println(Thread.currentThread().getName() + "发出通知");
            } finally {
            }
        }, "t2").start();
//        extracted();
    }

    private static void extracted() {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "被唤醒");
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "发出通知");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}

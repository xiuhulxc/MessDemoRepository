package com.example.demo1.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/9/1 23:10
 * @version: 1.0
 */
public class StampedLockDemo {
    static int number = 37;
    static StampedLock stampedLock = new StampedLock();

    public void write() {
        long l = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + "写入");
        try {
            number = number + 13;
        } finally {
            stampedLock.unlockWrite(l);
        }
        System.out.println(Thread.currentThread().getName() + "修改结束");

    }

    public void read() {
        long readLock = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName() + "come in");
        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "正在读取");
        }

        try {
            int result = number;
            System.out.println(Thread.currentThread().getName() + "获得成员变量:" + result);
            System.out.println(Thread.currentThread().getName() + "写程序没有修改成功,读锁 时间 写锁无法介入");

        } finally {
            stampedLock.unlockRead(readLock);
        }

    }

    public static void main(String[] args) {
        StampedLockDemo stampedLockDemo = new StampedLockDemo();
        new Thread(stampedLockDemo::read,"ThreadRead").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName() + "come");
            stampedLockDemo.write();
        },"ThreadWrite").start();
    }
}

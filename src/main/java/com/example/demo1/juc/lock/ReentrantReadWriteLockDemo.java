package com.example.demo1.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/9/1 21:48
 * @version: 1.0
 */

class MyResource {
    Map<String, String> map = new HashMap<>();
    Lock lock = new ReentrantLock();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void write(String key, String value) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在写入");
            map.put(key, value);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "完成写入");

        } finally {
            lock.unlock();
        }
    }

    public void read(String key) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在读取");
            String result = map.get(key);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "完成读取" + result);
        } finally {
            lock.unlock();
        }
    }
}

public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource();
        for (int i = 0; i < 10; i++) {
            final int i1 = i;
            new Thread(() -> {
                myResource.write(i1 + "", i1 + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 10; i++) {
            final int i1 = i;
            new Thread(() -> {
                myResource.read(i1 + "");
            }, String.valueOf(i)).start();
        }
    }
}

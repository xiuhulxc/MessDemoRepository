package com.example.demo1.juc.future;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/31 16:25
 * @version: 1.0
 */

class House {
    int saleCount = 0;

    public synchronized void saleHouse() {
        ++saleCount;
    }

    //    ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
//        @Override
//        protected Integer initialValue() {
//            return 0;
//        }
//    };
    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public void saleVolumeByThreadLocal() {
        threadLocal.set(1 + threadLocal.get());
    }
}

public class ThreadLocalDemo {
    public static void main(String[] args) {
        House house = new House();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                int size = new Random().nextInt(5) + 1;
                try {
                    for (int j = 0; j < size; j++) {
                        house.saleHouse();
                        house.saleVolumeByThreadLocal();
                    }
                    System.out.println(Thread.currentThread().getName()+"卖出:"+house.threadLocal.get());
                }finally {
                    house.threadLocal.remove();
                }
            }, String.valueOf(i)).start();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + house.saleCount);
    }
}

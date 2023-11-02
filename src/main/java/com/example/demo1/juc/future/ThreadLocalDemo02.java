package com.example.demo1.juc.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/31 17:13
 * @version: 1.0
 */
class MyData {
    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public void add() {
        threadLocal.set(1 + threadLocal.get());
    }
}

public class ThreadLocalDemo02 {

    public static void main(String[] args) {
        MyData myData = new MyData();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i < 10; i++) {
                try {
                    executorService.submit(() -> {
                        Integer integer = myData.threadLocal.get();
                        System.out.println();
                        myData.add();
                        Integer integer1 = myData.threadLocal.get();
                        System.out.println(Thread.currentThread().getName() + "初始值" + integer + "结束值" + integer1);
                    });
                } finally {
                    myData.threadLocal.remove();
                }

            }
        } finally {
            executorService.shutdown();
        }
    }
}

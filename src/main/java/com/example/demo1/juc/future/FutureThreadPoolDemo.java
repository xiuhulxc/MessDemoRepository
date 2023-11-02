package com.example.demo1.juc.future;

import java.util.concurrent.*;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/25 15:37
 * @version: 1.0
 * 线程池
 */
public class FutureThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //3个任务单线程,耗时
//        extracted();
        long currentTimeMillis = System.currentTimeMillis();

        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task1,over";
        });
        threadPool.submit(futureTask1);
        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task2,over";
        });
        threadPool.submit(futureTask2);
        FutureTask<String> futureTask3 = new FutureTask<>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task2,over";
        });
        threadPool.submit(futureTask3);
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());
        System.out.println(futureTask3.get());

        threadPool.shutdown();
        long timeMillis = System.currentTimeMillis();
        System.out.println(timeMillis - currentTimeMillis);
    }

    private static void extracted() {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            TimeUnit.MILLISECONDS.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.MILLISECONDS.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long timeMillis = System.currentTimeMillis();
        System.out.println(timeMillis - currentTimeMillis);
    }
}

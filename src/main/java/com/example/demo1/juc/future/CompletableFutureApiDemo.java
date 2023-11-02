package com.example.demo1.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/28 14:07
 * @version: 1.0
 */
public class CompletableFutureApiDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.println("11");
            return 1;
        },threadPool).thenApply(f -> {
            System.out.println("222");
            return f + 1;
        }).thenApply(f -> {
            System.out.println("33");
            return f + 3;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("..计算结果" + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });
        System.out.println(Thread.currentThread().getName() + "..主线程去忙其他任务");
        threadPool.shutdown();
    }
}

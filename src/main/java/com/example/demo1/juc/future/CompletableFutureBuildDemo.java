package com.example.demo1.juc.future;

import java.util.concurrent.*;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/25 16:24
 * @version: 1.0
 */
public class CompletableFutureBuildDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            System.out.println(Thread.currentThread().getName());
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException interruptedException) {
//                interruptedException.printStackTrace();
//            }
//        },executorService);
//        System.out.println(completableFuture.get());
//        extracted(executorService);
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ",come in..");
            int nextInt = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.println("1s出现结果:" + nextInt);
            return nextInt;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("...计算完成 ,无异常" + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println("异常情况" + e.getCause() + e.getMessage());
            return null;
        });
        System.out.println(Thread.currentThread().getName() + "线程先去完成其他");

    }

    private static void extracted(ExecutorService executorService) throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            return "sss";
        }, executorService);
        System.out.println(completableFuture.get());
        executorService.shutdown();
    }
}

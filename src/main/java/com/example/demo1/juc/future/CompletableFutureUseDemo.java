package com.example.demo1.juc.future;


import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/25 16:39
 * @version: 1.0
 */
public class CompletableFutureUseDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {

        }finally {
            threadPool.shutdown();
        }
        //        CompletableFuture.supplyAsync(() ->{
//            System.out.println(Thread.currentThread().getName()+"come in");
//            int result = ThreadLocalRandom.current().nextInt(10);
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException interruptedException) {
//                interruptedException.printStackTrace();
//            }
//            System.out.println("结构 :" + result);
//            return result;
//        });
    }
}

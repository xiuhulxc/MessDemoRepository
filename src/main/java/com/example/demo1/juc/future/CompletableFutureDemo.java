package com.example.demo1.juc.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/25 15:23
 * @version: 1.0
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new MyThread2());

        Thread t1 = new Thread(futureTask, "t1");
        t1.start();
        System.out.println(futureTask.get());
    }
}



class MyThread2 implements Callable<String>{
    @Override
    public String call() throws Exception {
        System.out.println("---come in call()");
        return "hello callable";
    }
}

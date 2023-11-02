package com.example.demo1.juc.future;

import java.sql.Time;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/25 15:51
 * @version: 1.0
 */
public class FutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + "---come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            return "task over";
        });
        Thread ti = new Thread(futureTask, "ti");
        ti.start();
        System.out.println(Thread.currentThread().getName()+"---忙其他 任务 ");
        while (true){
            if (futureTask.isDone()){
                System.out.println(futureTask.get());
                break;
            }else {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("正在处理中...");
            }
        }
    }
}

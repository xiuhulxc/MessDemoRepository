package com.example.demo1.rabbitmq;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/7/31 16:12
 * @version: 1.0
 */
public class SleepUtils {

    public static void sleep(int second){
        try {
            Thread.sleep(1000L *second);

        }catch (InterruptedException _ignored){
            Thread.currentThread().interrupt();
        }
    }
}

package com.example.demo1.juc.future;

import java.util.concurrent.TimeUnit;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/28 15:14
 * @version: 1.0
 */
class Phone{
    public synchronized void sendEmail(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        System.out.println("----sendEmail");
    }
    public synchronized void senSMS(){
        System.out.println("----sendSMS");
    }
}

public class Lock8Demo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new  Thread(()->{
            phone.sendEmail();
        },"a").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        new  Thread(()->{
            phone.senSMS();
        },"b").start();

    }
}

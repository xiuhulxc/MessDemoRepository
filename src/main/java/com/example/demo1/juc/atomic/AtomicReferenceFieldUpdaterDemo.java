package com.example.demo1.juc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/30 19:18
 * @version: 1.0
 */
class MyVar{
    public volatile Boolean isInit = Boolean.FALSE;
    AtomicReferenceFieldUpdater<MyVar,Boolean>  referenceFieldUpdater  =  AtomicReferenceFieldUpdater.newUpdater(MyVar.class,Boolean.class,"isInit");
    public void init(MyVar myVar){
        if (referenceFieldUpdater.compareAndSet(myVar,Boolean.FALSE,Boolean.TRUE)) {
            System.out.println(Thread.currentThread().getName()+":开始");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":over");
        }else {
            System.out.println(Thread.currentThread().getName()+":已经有线程进行初始化");

        }
    }
}
public class AtomicReferenceFieldUpdaterDemo {
    public static void main(String[] args) {
        MyVar myVar = new MyVar();
        for (int i = 0; i < 5; i++) {
            new Thread(() ->{
                myVar.init(myVar);
            },String.valueOf(i)).start();
        }
    }
}



package com.example.demo1.juc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/30 18:47
 * @version: 1.0
 */
public class AtomicMarkableReferenceDemo {
    static AtomicMarkableReference<Integer> markableReference = new AtomicMarkableReference<>(100, false);




    public static void main(String[] args) {
        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + ":" + marked);
            try {
                TimeUnit.MILLISECONDS.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            markableReference.compareAndSet(100, 1000, marked, !marked);

        }, "t1").start();
        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            try {
                TimeUnit.MILLISECONDS.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = markableReference.compareAndSet(100, 2000, marked, !marked);
            System.out.println(b);
            System.out.println(markableReference.isMarked() + ":" + markableReference.getReference());

        }, "t2").start();
    }
}

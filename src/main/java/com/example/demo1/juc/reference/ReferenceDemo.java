package com.example.demo1.juc.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/31 18:41
 * @version: 1.0
 */

class MyObject {
    @Override
    protected void finalize() throws Throwable {
        //在对象被不可撤销的丢弃之前执行清理操作
        System.out.println("----invoke finalize method!!");
    }
}

public class ReferenceDemo {

    public static void main(String[] args) {
//        strongReference();
//        softReference();
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> myObjectReferenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject, myObjectReferenceQueue);
        System.out.println(phantomReference.get());
        List<byte[]> list = new ArrayList<>();
        new Thread(() -> {
            while (true) {
                list.add(new byte[1 * 1024 * 1024]);
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(phantomReference.get() + "list  add  ok");
            }
        }, "t1").start();
        new Thread(() -> {
            while (true) {
                Reference<? extends MyObject> poll = myObjectReferenceQueue.poll();
                if (poll  != null){
                    System.out.println("已经有虚对象被回收,加入了队列" + poll);
                    break;
                }
            }
        }, "t2").start();
    }

    /**
     * 软引用
     */
    private static void softReference() {
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        System.out.println("---SoftReference" + softReference.get());
        System.gc();
        System.out.println("gc" + softReference.get());
    }

    /**
     * 强引用
     */
    private static void strongReference() {
        MyObject myObject = new MyObject();
        System.out.println("gc before:" + myObject);
        myObject = null;
        System.gc();
    }
}

package com.example.demo1.juc.future;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/28 15:46
 * @version: 1.0
 */

public class LockSyncDemo {

    Object object =  new Object();
    public void m1(){
        synchronized (object){
            System.out.println("--hello synchronized code lock");
        }
    }

    public static void main(String[] args) {

    }
}

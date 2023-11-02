package com.example.demo1.juc.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/30 15:28
 * @version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
class User{
    String  username;
    int  age;
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        User user = new User("张三", 5);
        User user1 = new User("李四", 8);
        userAtomicReference.set(user);
        boolean b = userAtomicReference.compareAndSet(user, user1);
        System.out.println(b+":"+userAtomicReference.get());
    }
}

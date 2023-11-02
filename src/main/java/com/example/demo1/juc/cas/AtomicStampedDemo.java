package com.example.demo1.juc.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/30 16:10
 * @version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
class Book{
    private int id;
    private String  bookName;
}

public class AtomicStampedDemo {
    public static void main(String[] args) {
        Book javaBook = new Book(1, "javaBook");
        AtomicStampedReference<Book> bookAtomicReference = new AtomicStampedReference<>(javaBook,1);
        System.out.println(bookAtomicReference.getReference()+":"+bookAtomicReference.getStamp());
        Book mysqlBook = new Book(2, "mysqlBook");
        boolean b;
        b=bookAtomicReference.compareAndSet(javaBook,mysqlBook, bookAtomicReference.getStamp(), bookAtomicReference.getStamp()+1);
        System.out.println(b+":"+bookAtomicReference.getReference()+":"+bookAtomicReference.getStamp());
        b=bookAtomicReference.compareAndSet(mysqlBook,javaBook, bookAtomicReference.getStamp(), bookAtomicReference.getStamp()+1);
        System.out.println(b+":"+bookAtomicReference.getReference()+":"+bookAtomicReference.getStamp());


    }
}

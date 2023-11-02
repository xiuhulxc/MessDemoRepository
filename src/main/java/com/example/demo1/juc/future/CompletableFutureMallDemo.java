package com.example.demo1.juc.future;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Access;
import java.util.concurrent.CompletableFuture;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/28 13:21
 * @version: 1.0
 */
public class CompletableFutureMallDemo {
    public static void main(String[] args) {
//        Student student = new Student();
//        student.setId(12).setStudentName("lxc").setMajor("xx");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "hello 1234";
        });
        System.out.println(future.join());
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
class Student {
    private Integer id;
    private String studentName;
    private String major;
}

package com.example.demo1;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/6/16 8:39
 * @version: 1.0
 */
@SpringBootTest
public class listTest {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 5, 8,10,11,6));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8, 8,10,11));

        for (int i = 0; i < list1.size(); i++) {
            for (Integer integer : list2) {
                if (list1.get(i).equals(integer)) {
                    list1.remove(i);
                }
            }
        }

        System.out.println(list1);
    }
}

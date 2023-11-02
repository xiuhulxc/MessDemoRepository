package com.example.demo1.juc.atomic;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/30 19:33
 * @version: 1.0
 */
public class LongAdderApiDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.increment();
        System.out.println(longAdder.sum());

        LongAccumulator longAccumulator = new LongAccumulator(Long::sum,5);
        longAccumulator.accumulate(5);
        longAccumulator.accumulate(6);
        longAccumulator.accumulate(6);
        System.out.println(longAccumulator.get());

    }
}

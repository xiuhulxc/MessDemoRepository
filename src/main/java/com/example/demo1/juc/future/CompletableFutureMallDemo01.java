package com.example.demo1.juc.future;

import lombok.Getter;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/28 13:34
 * @version: 1.0
 */
public class CompletableFutureMallDemo01 {

    static List<NetMall> list = Arrays.asList(new NetMall("jd"), new NetMall("dd"), new NetMall("tb"));

    public static List<String> getPrice(List<NetMall> list, String productName) {
        return list.stream()
                .map(netMall -> String.format(productName + " in %s price is %.2f", netMall.getNetMallName(), netMall.calPrice(productName))).collect(Collectors.toList());
    }

    public static List<String> getPriceByCompletableFuture(List<NetMall> netMalls,String productName){
        return netMalls.stream()
                .map(netMall -> CompletableFuture.supplyAsync(() -> String.format(productName + "in %s price is %.2f", netMall.getNetMallName(), netMall.calPrice(productName))))
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }

    public static void main(String[] args) {
//        System.out.println(ThreadLocalRandom.current().nextDouble() * 2 + "mysql".charAt(0));
        long currentTimeMillis = System.currentTimeMillis();
        List<String> mysql = getPriceByCompletableFuture(list, "mysql");
        for (String elem:mysql){
            System.out.println(elem);
        }
        long timeMillis = System.currentTimeMillis();
        System.out.println("时间" + (timeMillis - currentTimeMillis));
    }
}

class NetMall {
    @Getter
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double calPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}

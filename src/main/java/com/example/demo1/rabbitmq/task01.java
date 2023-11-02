package com.example.demo1.rabbitmq;

import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/7/27 14:14
 * @version: 1.0
 */
public class task01 {
    private static final String QUEUE_NAME  = "hello";

    //发送大量的消息
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtil.getChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String next = scanner.next();
            channel.basicPublish("",QUEUE_NAME,null,next.getBytes(StandardCharsets.UTF_8));
            System.out.println("生产者发出消息:" + next);
        }
    }
}

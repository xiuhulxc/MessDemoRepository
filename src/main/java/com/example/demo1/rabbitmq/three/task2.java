package com.example.demo1.rabbitmq.three;

import com.example.demo1.rabbitmq.RabbitMqUtil;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/7/31 15:55
 * 消息在手动应答时是不丢失的,放回队列中重新消费
 * @version: 1.0
 */
public class task2 {


    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtil.getChannel();
        //声明一个队列
        boolean durable = true;
        channel.queueDeclare(TASK_QUEUE_NAME,durable,false,false,null);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String next = scanner.next();
            channel.basicPublish("",TASK_QUEUE_NAME,null,next.getBytes(StandardCharsets.UTF_8));
            System.out.println("生产者发出消息:" + next);
        }
    }
}

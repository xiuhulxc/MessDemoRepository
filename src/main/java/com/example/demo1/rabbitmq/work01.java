package com.example.demo1.rabbitmq;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/7/27 14:02
 * @version: 1.0
 */
public class work01 {

    private static final String QUEUE_NAME  = "hello";

    public static void main(String[] args) {
        try {
            Channel channel = RabbitMqUtil.getChannel();
            DeliverCallback deliverCallback =  (consumerTag, message) -> {
                System.out.println("接收的消息work01" + new String(message.getBody()));
            };
            //取消消息的回调
            CancelCallback cancelCallback = consumerTag -> {
                System.out.println("work01消费消息被中断");
            };
            //消息的接收
            System.out.println("work02等待...");
            channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
        }catch (Exception  e){
            System.out.println(e.getMessage());
        }
    }
}

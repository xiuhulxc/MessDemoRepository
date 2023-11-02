package com.example.demo1.rabbitmq.six;

import com.example.demo1.rabbitmq.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/10 15:14
 * @version: 1.0
 */
public class ReceiveLogsDirect02 {

    public static final String CHANGE_NAME = "direct_logs";


    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtil.getChannel();
        //声明一个交换机
        channel.exchangeDeclare(CHANGE_NAME, BuiltinExchangeType.DIRECT);
        //声明一个队列
        channel.queueDeclare("disk", false, false, false, null);
        channel.queueBind("disk", CHANGE_NAME, "error");

        System.out.println("等待接收消息");
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("ReceiveLogsDirect02接收到的消息为:" + new String(message.getBody(), StandardCharsets.UTF_8));
        };
        channel.basicConsume("disk", true, deliverCallback, consumerTag -> {
        });
    }
}

package com.example.demo1.rabbitmq.five;

import com.example.demo1.rabbitmq.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/10 14:53
 * @version: 1.0
 * 消息接收02
 */
public class ReceiveLogs02 {

    //交换机名称
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtil.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        //声明临时队列(不需要取名字),队列的名称是随机的,当消费者断开与队列的名称,队列则删除
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        System.out.println("等待接收消息");
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("02接收到的消息为:" + new String(message.getBody(), "UTF-8"));
        };

        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }
}

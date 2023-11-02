package com.example.demo1.rabbitmq.seven;

import com.example.demo1.rabbitmq.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/10 16:38
 * @version: 1.0
 */
public class ReceiveLogsTopic02 {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception{

        //接收消息
        Channel channel = RabbitMqUtil.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String queueName = "Q2";
        channel.queueDeclare(queueName,false,false,false,null);
        channel.queueBind(queueName,EXCHANGE_NAME,"*.*.rabbit");
        channel.queueBind(queueName,EXCHANGE_NAME,"lazy.#");
        System.out.println("ReceiveLogsTopic02等待接收消息...");
        DeliverCallback deliverCallback = (consumerTag,message) -> {
            System.out.println("ReceiveLogsTopic02接收到的消息:" + new String(message.getBody()));
            System.out.println("ReceiveLogsTopic02接收队列:" + queueName + "绑定键" + message.getEnvelope().getRoutingKey());
        };
        channel.basicConsume(queueName,true,deliverCallback,consumerTag -> {});
    }
}

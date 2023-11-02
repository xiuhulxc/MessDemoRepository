package com.example.demo1.rabbitmq.eight;

import com.example.demo1.rabbitmq.RabbitMqUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/11 23:38
 * @version: 1.0
 * 死信队列
 * 消费者01
 */
public class Consumer01 {

    //普通交换机
    public static final String NORMAL_EXCHANGE = "normal_exchange";
    //死信交换机
    public static final String DEAD_EXCHANGE = "dead_exchange";
    //普通队列
    public static final String NORAML_QUEUE = "noraml_queue";
    //死信队列
    public static final String DEAD_QUEUE =  "dead_queue";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtil.getChannel();
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE,BuiltinExchangeType.DIRECT);
        //声明普通队列
        //过期时间 10s
        Map<String,Object> arguments = new HashMap<>();
//        arguments.put("x-message-ttl",10000);
        arguments.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        arguments.put("x-dead-letter-routing-key","lisi");
        arguments.put("x-max-length",6);
        channel.queueDeclare(NORAML_QUEUE,false,false,false,arguments);
        //声明死信队列
        channel.queueDeclare(DEAD_QUEUE,false,false,false,null);

        channel.queueBind(NORAML_QUEUE,NORMAL_EXCHANGE,"zhangsan");
        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"lisi");

        System.out.println("等待接收消息");
        DeliverCallback deliverCallback = (consumerTag,message) -> {
            System.out.println("consumer01接收的消息:" + new String(message.getBody()));

        };
        channel.basicConsume(NORAML_QUEUE,true,deliverCallback,consumerTag->{});
    }

}

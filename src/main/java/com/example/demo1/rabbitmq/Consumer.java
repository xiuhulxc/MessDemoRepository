package com.example.demo1.rabbitmq;

import com.rabbitmq.client.*;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/7/27 13:22
 *消费者代码
 *  @version: 1.0
 */
public class Consumer {

    public static final String QUEUE_NAME = "hello";

    // 接收消息
    public static void main(String[] args) {
        //创建链接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //工厂ip
        factory.setHost("192.168.124.2");
        factory.setPort(5674);
        //用户名
        factory.setUsername("admin");
        factory.setPassword("123456");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            /**
             * 接收消息
             */
            DeliverCallback deliverCallback =  (consumerTag,message) -> { System.out.println("接收的消息" + new String(message.getBody())); };

            //取消消息的回调
            CancelCallback cancelCallback = consumerTag -> {
                System.out.println("消费消息被中断");
            };
            channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

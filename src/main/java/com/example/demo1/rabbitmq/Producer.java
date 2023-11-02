package com.example.demo1.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/7/26 14:42
 * @version: 1.0
 */
public class Producer {

    private static final String QUEUE_NAME = "hello";

    //发消息
    public static void main(String[] args) throws Exception {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //工厂ip
        factory.setHost("192.168.124.2");
        factory.setPort(5674);
        //用户名
        factory.setUsername("admin");
        factory.setPassword("123456");
        try {
            Connection connection = factory.newConnection();
            //获取信道
            Channel channel = connection.createChannel();
            //创建队列
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            //发送消息
            String message = "hello world";
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("发送ok");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

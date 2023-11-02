package com.example.demo1.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/7/27 13:56
 * @version: 1.0
 */
public class RabbitMqUtil {

    public static Channel getChannel() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.124.2");
        factory.setPort(5674);
        //用户名
        factory.setUsername("admin");
        factory.setPassword("123456");
        Connection connection = factory.newConnection();
        //获取信道
        return connection.createChannel();
    }
}

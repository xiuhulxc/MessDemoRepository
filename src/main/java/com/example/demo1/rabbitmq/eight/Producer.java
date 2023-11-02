package com.example.demo1.rabbitmq.eight;

import com.example.demo1.rabbitmq.RabbitMqUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/11 23:59
 * @version: 1.0
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtil.getChannel();
        AMQP.BasicProperties properties = new AMQP.BasicProperties()
                .builder()
                .expiration("10000").build();
        for (int i = 1; i < 11; i++) {
            String message = "info" + i;
            channel.basicPublish(Consumer01.NORMAL_EXCHANGE, "zhangsan", null, message.getBytes(StandardCharsets.UTF_8));

        }
    }
}

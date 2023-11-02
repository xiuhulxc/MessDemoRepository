package com.example.demo1.rabbitmq.six;

import com.example.demo1.rabbitmq.RabbitMqUtil;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/10 15:22
 * @version: 1.0
 */
public class DirectLogs {

    //交换机名称
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtil.getChannel();
//        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        Scanner scanner =  new Scanner(System.in);
        while (scanner.hasNext()){
            String next = scanner.next();
            channel.basicPublish(EXCHANGE_NAME,"error",null,next.getBytes(StandardCharsets.UTF_8));
            System.out.println("成功发送,消息为:" + next);

        }
    }
}


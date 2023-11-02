package com.example.demo1.rabbitmq.three;

import com.example.demo1.rabbitmq.RabbitMqUtil;
import com.example.demo1.rabbitmq.SleepUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/7/31 16:07
 * @version: 1.0
 */
public class work03 {
    public static final String TASK_QUEUE_NAME = "ack_queue";


    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtil.getChannel();
        System.out.println("C1等待接收消息处理");
        //采用手动应答
        DeliverCallback deliverCallback = (consumerTag,message) -> {
            //沉睡1s
            SleepUtils.sleep(1);
            System.out.println("接收到的消息:" + new String(message.getBody(),"UTF-8"));
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
        };

        //设置不公平分发
        int prefetchCount = 2;
        channel.basicQos(prefetchCount);
        boolean autoAck = false;
        channel.basicConsume(TASK_QUEUE_NAME,autoAck,deliverCallback,(consumerTag -> {
            System.out.println(consumerTag + "消费者取消消费接口的回调");
        }));

    }
}

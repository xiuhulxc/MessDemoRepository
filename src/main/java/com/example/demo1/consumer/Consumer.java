package com.example.demo1.consumer;

import com.example.demo1.config.ConfirmConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/14 15:26
 * @version: 1.0
 */
@Component
public class Consumer {

    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE_NAME)
    public void receiveConfirmMessage(Message message){
        System.out.println("接收到消息为:" + new String(message.getBody()));
    }
}

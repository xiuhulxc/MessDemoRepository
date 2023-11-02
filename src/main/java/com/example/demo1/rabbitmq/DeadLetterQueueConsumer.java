package com.example.demo1.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/12 23:43
 * @version: 1.0
 */
@Slf4j
@Component
public class DeadLetterQueueConsumer {

    @RabbitListener(queues = "QD")
    public void receiveD(Message message, Channel channel)throws Exception{
        String msg = new String(message.getBody());
        log.info("收到消息:" + msg);

    }
}

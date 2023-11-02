package com.example.demo1.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/14 15:46
 * @version: 1.0
 */
@Slf4j
@Component
public class MyCallBack implements RabbitTemplate.ConfirmCallback{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 交换机确认回调方法
     * 1.发消息交换机接收到了回调,
     * @param correlationData 保存回调消息的ID以及相关信息
     * @param ack             交换机收到消息 true
     * @param casuse          null /如果ack = false则是失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String casuse) {
        String id = correlationData != null ? correlationData.getId() : "";
        if (ack) {
            log.info("交换机已经收到了消息,成功,id:" + id);
        } else {
            log.info("交换机没有收到了消息id:{},失败,原因:{}", id, casuse);

        }
    }

}

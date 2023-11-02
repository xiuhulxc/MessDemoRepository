package com.example.demo1.controller;

import com.example.demo1.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/14 15:21
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/confirm")
public class ProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/senMsg/{message}")
    public void sendMessage(@PathVariable String message) throws Exception{
        CorrelationData correlationData = new CorrelationData("1");
        rabbitTemplate.convertAndSend(ConfirmConfig.EXCHANGE_NAME + 1,ConfirmConfig.CONFIRM_ROUTING_KEY,message,correlationData);
        log.info("发送消息内容为:" + message);
    }

}

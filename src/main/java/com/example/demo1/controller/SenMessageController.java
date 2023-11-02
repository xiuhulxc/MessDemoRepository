package com.example.demo1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/12 23:35
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/ttl")
public class SenMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable String message) throws Exception {
        log.info("发送一条消息给两个ttl队列," + message);
        rabbitTemplate.convertAndSend("X", "XA", "消息来紫ttl为10s的队列:" + message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来紫ttl为20s的队列:" + message);
        log.info("结束");
    }
    @GetMapping("/sendExpirationMsg/{message}/{ttlTime}")
    public void sendMsg(@PathVariable String message,@PathVariable String ttlTime) throws Exception{
        log.info("消息为:" + message + "时间为:" + ttlTime);
        rabbitTemplate.convertAndSend("X","XC",message,msg -> {
            msg.getMessageProperties().setExpiration(ttlTime);
            return msg;
        });
    }

    //开始发消息  测试确认

}

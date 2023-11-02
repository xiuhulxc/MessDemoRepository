package com.example.demo1.ttl;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions: ttl配置文件类
 * @author: Liu XuChao
 * @date: 2023/8/12 23:21
 * @version: 1.0
 */

@Configuration
public class TtlQueueConfig {

    public static final String X_EXCHANGE = "X";
    public static final String Y_DEAD_EXCHANGE = "Y";
    public static final String QUEUE_A = "QA";
    public static final String QUEUE_B = "QB";
    public static final String DEAD_QUEUE_D = "QD";
    public static final String QUEUE_C = "QC";


    //声明交换机别名
    @Bean("xExchange")
    public DirectExchange xExchange() {
        return new DirectExchange(X_EXCHANGE);
    }

    //声明交换机别名
    @Bean("yExchange")
    public DirectExchange yExchange() {
        return new DirectExchange(Y_DEAD_EXCHANGE);
    }

    //声明队列
    @Bean("queueA")
    public Queue queueA() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", Y_DEAD_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", "YD");
        arguments.put("x-message-ttl", 10000);
        return QueueBuilder.durable(QUEUE_A).withArguments(arguments).build();
    }

    //声明队列
    @Bean("queueB")
    public Queue queueB() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", Y_DEAD_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", "YD");
        arguments.put("x-message-ttl", 20000);
        return QueueBuilder.durable(QUEUE_B).withArguments(arguments).build();
    }

    @Bean("queueD")
    public Queue queueD() {
        return QueueBuilder.durable(DEAD_QUEUE_D).build();
    }

    @Bean("queueC")
    public Queue queueC() {
        Map<String, Object> arguments = new HashMap<>(3);
        arguments.put("x-dead-letter-exchange", Y_DEAD_EXCHANGE);
        arguments.put("x-dead-letter-routing-key", "YD");
        return QueueBuilder.durable(QUEUE_C).withArguments(arguments).build();
    }

    //绑定
    @Bean
    public Binding queueABingdingX(@Qualifier("queueA") Queue queue, @Qualifier("xExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("XA");
    }

    @Bean
    public Binding queueBBingdingX(@Qualifier("queueB") Queue queue, @Qualifier("xExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("XB");
    }

    @Bean
    public Binding queueDBingdingX(@Qualifier("queueD") Queue queue, @Qualifier("yExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("YD");
    }

    @Bean
    public Binding queueCBingDingX(@Qualifier("queueC") Queue queue, @Qualifier("xExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("XC");
    }
}


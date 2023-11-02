package com.example.demo1.rabbitmq.four;

import com.example.demo1.rabbitmq.RabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;

import javax.swing.plaf.PanelUI;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @descriptions:
 * @author: Liu XuChao
 * @date: 2023/8/8 13:46
 * 发布确认模式
 * @version: 1.0
 */
public class Confirm {

    public static final int MESSAGE_COUNT = 1000;

    public static void main(String[] args) throws Exception {
//        Confirm.publishMessageIndividually();
//        Confirm.publishMessageBatch();
//        publishMessageAsync();
        publishMessageAsync();
    }

    /**
     * 1.单个确认
     * 2.批量确认
     * 3.异步确认
     */
    public static void publishMessageIndividually() throws Exception {
        Channel channel = RabbitMqUtil.getChannel();
        String string = UUID.randomUUID().toString();
        channel.queueDeclare(string, true, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        long begin = System.currentTimeMillis();
        //批量发消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", string, null, message.getBytes(StandardCharsets.UTF_8));
            boolean waitForConfirms = channel.waitForConfirms();
            if (waitForConfirms) {
                System.out.println("消息发送成功");
            }

        }
        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个单独确认消息,耗时" + (end - begin) + "ms");
    }


    //批量发布确认
    public static void publishMessageBatch() throws Exception {
        Channel channel = RabbitMqUtil.getChannel();
        String string = UUID.randomUUID().toString();
        channel.queueDeclare(string, true, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        int batchSize = 100;
        long begin = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", string, null, message.getBytes(StandardCharsets.UTF_8));
            if (i % batchSize == 0) {
                channel.waitForConfirms();
                long end = System.currentTimeMillis();
                System.out.println("发布" + MESSAGE_COUNT + "个批量确认消息,耗时" + (end - begin) + "ms");
            }

        }

    }

    /**
     * 异步发布确认
     */
    public static void publishMessageAsync() throws Exception {
        Channel channel = RabbitMqUtil.getChannel();
        String string = UUID.randomUUID().toString();
        channel.queueDeclare(string, true, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        long begin = System.currentTimeMillis();

        /**
         * 线程安全有序的 一个哈希表,适用于高并发的情况下
         * 1.将序号和消息关联
         * 2.根据序号删除条数
         * 3.支持高并发(多线程)
         */
        ConcurrentSkipListMap<Long, String> outstandingConfirms = new ConcurrentSkipListMap<>();
        // 消息确认成功,回调函数
        ConfirmCallback ackCallback = (deliverTag, multiple) -> {
            if (multiple) {
                //批量确认
                ConcurrentNavigableMap<Long, String> confirmed = outstandingConfirms.headMap(deliverTag);
                confirmed.clear();
            } else {
                //单个确认直接删除
                outstandingConfirms.remove(deliverTag);
            }

            System.out.println("确认的消息" + deliverTag);
        };
        // 消息确认失败,回调函数
        ConfirmCallback nackCallback = (deliverTag, multiple) -> {
            String message = outstandingConfirms.get(deliverTag);
            System.out.println("未确认的消息:" + message + ":::未确认的消息tag" + deliverTag);
        };
        //准备消息监听器,监听那些消息成功,那些消息没成功
        channel.addConfirmListener(ackCallback, nackCallback);
        //异步逻辑
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = "消息" + i;
            channel.basicPublish("", string, null, message.getBytes(StandardCharsets.UTF_8));
            outstandingConfirms.put(channel.getNextPublishSeqNo(), message);
        }
        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个异步确认消息,耗时" + (end - begin) + "ms");
    }
}

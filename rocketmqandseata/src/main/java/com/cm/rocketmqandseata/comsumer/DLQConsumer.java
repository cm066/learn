package com.cm.rocketmqandseata.comsumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


/**
 * 监听死信队列
 */
@Component
@RocketMQMessageListener(topic = "%RETRY%tx-consumer4",
        consumerGroup = "tx-consumer")
public class DLQConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String msg) {
        System.out.println("从重试队列里面获取到的消息：" + msg);
    }
}

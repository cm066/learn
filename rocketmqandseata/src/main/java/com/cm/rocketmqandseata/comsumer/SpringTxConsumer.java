package com.cm.rocketmqandseata.comsumer;

import com.cm.rocketmqandseata.entity.Msg;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "rocket-mq1",
        consumerGroup = "tx-consumer4")
public class SpringTxConsumer implements RocketMQListener<Msg> {
    @Override
    public void onMessage(Msg msg) {
        System.out.println("接收到消息 -> " + msg);
    }
}

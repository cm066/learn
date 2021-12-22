package com.cm.product;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.Set;

public class Consumer1 {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("cg");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setMaxReconsumeTimes(3);
        consumer.subscribe("someTopic", "*");
//        consumer.start();
        Set<MessageQueue> someTopic = consumer.fetchSubscribeMessageQueues("someTopic");
        System.out.println(someTopic.size());
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                for (MessageExt msg : msgs) {
//                    System.out.println(msg);
//                }
//                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//            }
//        });
        consumer.start();
        System.out.println("end");
    }
}

package com.cm.product;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.TimeUnit;


/**
 * 异步发送消息
 */
public class AyncProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        //指定生产组队的名称
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        producer.setNamesrvAddr("192.168.224.120:9876");
        producer.start();
        //默认为2次，这个是发送失败后重发的次数
        producer.setRetryTimesWhenSendFailed(3);
        producer.setDefaultTopicQueueNums(6);//默认的队列是生成4个队列
        for (int i = 0; i < 100; i++) {
            byte[] body = ("Hi," + i).getBytes();
            Message msg = new Message("myTopic34", "myTag", body);
            msg.setKeys("my"+i);
            producer.send(msg, new SendCallback() {
                //当producer接收到mq发送来的ack后，就会触发该回调函数
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println(sendResult);
                }
                @Override
                public void onException(Throwable throwable) {
                    throwable.printStackTrace();
                }
            });

        }
        TimeUnit.SECONDS.sleep(3);
        producer.shutdown();
    }
}

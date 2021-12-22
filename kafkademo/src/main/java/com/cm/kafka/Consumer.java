package com.cm.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

/**
 * @author Administrator
 */
public class Consumer {
    private static KafkaConsumer<String, String> consumer;
    public static void main(String[] args) {
        //consumer 的配置属性
        Properties props = new Properties();

        ///brokers 地址
        props.put("bootstrap.servers", "192.168.224.120:9092");

        //指定该 consumer 将加入的消费组
        props.put("group.id", "test22");
        // 开启自动提交 offset，关于offset提交，我们后续再来详细说说
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");

        //指定序列化类
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        //创建 consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        //订阅消费主题，这里一个消费者可以同时消费 foo 和 bar 两个主题的数据
        consumer.subscribe(Arrays.asList("test1"),new HandleRebalance());
        while (true) {
//            System.out.println(consumer);
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
            consumer.commitSync();
        }
    }

    private static class HandleRebalance implements ConsumerRebalanceListener{
        private Map<TopicPartition, OffsetAndMetadata> topicPartitionOffsetAndMetadataMap = new HashMap<>();
        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> collection) {
            System.out.println("Lost partitions in rebalance.Committing current offsets: "+ topicPartitionOffsetAndMetadataMap);
            consumer.commitSync(topicPartitionOffsetAndMetadataMap);
        }

        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
            for (TopicPartition partition : partitions) {
                consumer.seek(partition,getOffsetFromDB(partition));
            }
        }

        /**
         * 这个就是在进行再度分区的时候，可以去数据库里面查每个分区的消费情况
         * @param partition
         * @return
         */
        public static Long getOffsetFromDB(TopicPartition partition){
            return 1L;
        }
    }
}


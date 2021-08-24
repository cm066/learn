package com.cm.business.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.cm.business.entity.Msg;
import com.cm.business.service.BuisnessService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//@Component
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "myTransactionGroup")
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    @Autowired
    private BuisnessService buisnessService;

    /**
     * 执行业务逻辑
     *
     * @param message
     * @param o
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            String s = new String((byte[]) message.getPayload());
            Msg msg = JSON.parseObject(s, Msg.class);
            log.error("发送半消息{}成功，执行其他事务消息", msg);
            // 执行本地事务和利用seata AT模式保证分布式的完成
            boolean b = buisnessService.toOrder(msg.getProductId(), msg.getCurrentUse());
            if (b) { //本地事务和seata事务执行成功，让rocketmq发送消息
                // 返回事务状态给生产者
                log.info("其他事务执行成功结果为{}，先提交RocketMQ事务消息", b);
                return RocketMQLocalTransactionState.COMMIT;
            } else {
                log.info("其他事务执行失败，回滚RocketMQ事务消息");
                return RocketMQLocalTransactionState.ROLLBACK;
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.info("发生异常，回查事务信息");
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }

    /**
     * 当消息发送到rocketmq失败回调执行的方法
     * 回查事务消息
     *
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String transId = (String) message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        //在这里回查其他事务的信息，根据其他事务的信息来执行
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}

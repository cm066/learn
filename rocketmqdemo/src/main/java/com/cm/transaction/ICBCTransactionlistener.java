package com.cm.transaction;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

public class ICBCTransactionlistener implements TransactionListener {


    /**
     * 消息预提交成功后就会触发该方法的执行,用于完成本地事务
     *
     * @param message
     * @param o
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        System.out.println("预提交消息成功:" + message);
        if (StringUtils.equals("TAGA", message.getTags())) {
            return LocalTransactionState.COMMIT_MESSAGE;
        } else if (StringUtils.equals("TAGB", message.getTags())) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        } else if (StringUtils.equals("TAGC", message.getTags())) {
            return LocalTransactionState.UNKNOW;
        }
        return LocalTransactionState.UNKNOW;
    }


    /**
     * 消息回查
     * 引发消息回查的原因最常见的有两个
     * 1)回调操作中返回消息回查
     * 2)TC没有接收到TM的最终全局事务确认指令
     */

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        System.out.println("执行消息回查" + messageExt.getTags());
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}

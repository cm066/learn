package com.cm.business.service;

import com.cm.business.entity.Msg;
import io.seata.core.exception.TransactionException;

public interface BuisnessService {

     boolean toOrder(Long productId,int currentUse) throws TransactionException;

     void toSendMsg(Msg msg);
}

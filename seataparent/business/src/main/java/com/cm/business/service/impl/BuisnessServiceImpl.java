package com.cm.business.service.impl;

import com.cm.business.client.OrderClient;
import com.cm.business.client.StorageClient;
import com.cm.business.entity.Msg;
import com.cm.business.entity.R;
import com.cm.business.producer.SpringTransactionProducer;
import com.cm.business.service.BuisnessService;
import com.cm.business.service.IntegralsService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import lombok.SneakyThrows;

@Service
public class BuisnessServiceImpl implements BuisnessService {

    @Autowired
    private StorageClient storageClient;
    @Autowired
    private OrderClient orderClient;
    @Autowired
    private SpringTransactionProducer springTransactionProducer;
    @Autowired
    private IntegralsService integralsService;

    @GlobalTransactional
    @Override
    public boolean toOrder(Long productId, int currentUse)  {


//        try {
//            integralsService.toInsert();
//        } catch (TransactionException e) {
//
//            e.printStackTrace();
//            try {
//                GlobalTransactionContext.reload(RootContext.getXID()).rollback();
//            } catch (TransactionException transactionException) {
//                transactionException.printStackTrace();
//            }
//        }
        R.xid.put("1",RootContext.getXID());
        storageClient.redcuce2(productId, currentUse);
        System.out.println(RootContext.getXID());
        orderClient.insertOrder(productId, currentUse);
        integralsService.toInsert(RootContext.getXID());
        return true;
    }

    @Override
    public void toSendMsg(Msg msg) {
        springTransactionProducer.sendMsg("rocket-mq1", msg);
    }
}

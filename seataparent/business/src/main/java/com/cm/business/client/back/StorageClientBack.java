package com.cm.business.client.back;

import com.cm.business.client.StorageClient;
import com.cm.business.entity.R;
import com.cm.business.excption.MyExcption;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import org.springframework.stereotype.Component;

@Component
public class StorageClientBack implements StorageClient {
    @Override
    public boolean redcuce1(Long productId, int currentUse) {

//        feign远程调用失败，回滚执行全局事务
        try {
            GlobalTransactionContext.reload(RootContext.getXID()).rollback();
        } catch (TransactionException e) {
            e.printStackTrace();
        }
//        int i = 10 /0;
        System.out.println("远程调用Storage服务失败，执行熔断");
//        throw new MyExcption(20001,"远程调用失败，执行的服务的熔断");
        return false;
    }

    @Override
    public R redcuce2(Long productId, int currentUse) {
        return R.error();
    }
}

package com.cm.business.client.back;


import com.cm.business.client.OrderClient;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import org.springframework.stereotype.Component;

@Component
public class OrderClientBack implements OrderClient {

    @Override

    public boolean insertOrder(Long productId, Integer productNum) {
        System.out.println("远程OrderClientBack调用失败，执行了回滚");
        //feign远程调用失败，回滚执行全局事务
        try {
            GlobalTransactionContext.reload(RootContext.getXID()).rollback();
        } catch (TransactionException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String testZikpin() {
        return null;
    }
}

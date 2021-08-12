package com.cm.business.service.impl;


import com.cm.business.entity.Integrals;
import com.cm.business.entity.R;
import com.cm.business.mapper.IntegralMapper;
import com.cm.business.service.IntegralsService;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class IntegralsServiceImpl implements IntegralsService {

    @Autowired
    private IntegralMapper integralMapper;

    @Async //异步的去执行这个方法
    @Override
    public Object toInsert(String xid) {

        Integrals in = new Integrals();
        in.setCount(100);
        in.setUserId(989898L);
        System.err.println(Thread.currentThread().getId());
        R.xid.put(Thread.currentThread().getId()+"",xid);

        int i = 10 / 0;
        int insert = integralMapper.insert(in);
        System.out.println(R.xid.get("1"));
        System.err.println(Thread.currentThread().getId());

//        throw new RuntimeException("发生了异常");
        return R.error();

//        return R.ok();

    }
}

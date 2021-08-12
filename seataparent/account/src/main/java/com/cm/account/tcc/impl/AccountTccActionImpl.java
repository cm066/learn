package com.cm.account.tcc.impl;

import com.cm.account.entity.Account;
import com.cm.account.mapper.AccountMapper;
import com.cm.account.tcc.AccountTccAction;
import com.cm.account.tcc.ResultHold;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Slf4j
public class AccountTccActionImpl implements AccountTccAction {

    @Autowired
    private AccountMapper accountMapper;
    @Transactional
    @Override
    public boolean prepareDecreaseAccount( Long userId, Integer frozens) {

        log.info("减少账户金额，第一阶段锁定金额，userId="+userId+"， money="+frozens);

        Account account = accountMapper.selectById(1);
       if (account.getBalance() <= 0){
           throw new RuntimeException("账户金额不足");
       }
         /*
        余额-money
        冻结+money
         */
        boolean b = accountMapper.reduceAccount(userId, frozens);
        //保存标识
//        ResultHold.setResult(getClass(), businessActionContext.getXid(), "p");
        return true;
    }

    @Transactional
    @Override
    public boolean commit(BusinessActionContext businessActionContext) {

        long userId = Long.parseLong(businessActionContext.getActionContext("userId").toString());
        Integer money =  Integer.parseInt(businessActionContext.getActionContext("frozens").toString());
        log.info("减少冻结金钱，第二阶段，提交，userId="+userId+"， money="+money);
//        //防止重复提交
//        if (ResultHold.getResult(getClass(), businessActionContext.getXid()) == null) {
//            return true;
//        }
        boolean b = accountMapper.reduceFrozens(userId, money);
        return true;
    }
    @Transactional
    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {

        long userId = Long.parseLong(businessActionContext.getActionContext("userId").toString());
        Integer frozens =  Integer.parseInt(businessActionContext.getActionContext("frozens").toString());
        log.info("account服务 回滚事务");
//        //防止重复提交
//        if (ResultHold.getResult(getClass(), businessActionContext.getXid()) == null) {
//            return true;
//        }
        boolean b = accountMapper.addAccount(userId, frozens);
        return true;
    }
}

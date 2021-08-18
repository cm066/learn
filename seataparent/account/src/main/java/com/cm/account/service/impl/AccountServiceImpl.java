package com.cm.account.service.impl;

import com.cm.account.mapper.AccountMapper;
import com.cm.account.service.AccountService;
import com.cm.account.tcc.AccountTccAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountTccAction accountTccAction;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public boolean reduceAccount(Long userId, Integer frozens) {

        boolean b = accountTccAction.prepareDecreaseAccount( userId, frozens);
        int i = 10/0;
        return b;
    }

    @Override
    public boolean reduceAccount1(Long userId, Integer frozens) {
        boolean b = accountMapper.reduceAccount(userId, frozens);
        return b;
    }
}

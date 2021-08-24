package com.cm.account.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.cm.account.mapper.AccountMapper;
import com.cm.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class AccountController {


    @Autowired
    AccountMapper accountMapper;

    @Autowired
    private AccountService accountService;

    @GetMapping("/reduce/{userId}/{frozens}")
    public boolean updateMOney(@PathVariable Long userId, @PathVariable Integer frozens) {
        boolean b = accountService.reduceAccount(userId, frozens);
        return b;
    }

    //    @SentinelResource(value = )
    @GetMapping("/reduce1/{userId}/{frozens}")
    public boolean updateMOney1(@PathVariable Long userId, @PathVariable Integer frozens) {
        boolean b = accountService.reduceAccount1(userId, frozens);
        return b;
    }

    @SentinelResource(value = "te", fallback = "te1")
    @GetMapping("/te")
    public String te() {
        return "ok";
    }

    public String te1() {
        return "ok1";
    }
}

package com.cm.business.controller;


import com.cm.business.entity.Integrals;
import com.cm.business.entity.Msg;
import com.cm.business.entity.R;
import com.cm.business.service.BuisnessService;
import com.cm.business.service.IntegralsService;
import io.seata.core.exception.TransactionException;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("bui")
public class BuisnessController {

    @Autowired
    private BuisnessService buisnessService;

    @Autowired
    private IntegralsService integralsService;

    @GetMapping("toOrder/{productId}/{currentUse}")
    public String toOrder(@PathVariable Long productId, @PathVariable int currentUse){
        Msg msg = new Msg();
        msg.setProductId(productId);
        msg.setCurrentUse(currentUse);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        msg.setUuid(uuid);
        buisnessService.toSendMsg(msg);
//        int i = 10 / 0;
        return "ok";
    }

    @GetMapping("/insert")
    public String  insert(){
        Integrals in = new Integrals();
        in.setCount(100);
        in.setUserId(989898L);
//        integralsService.toInsert();
        return "ok";
    }

    @GetMapping("/insert1")
    public R insert1(){
        Integrals in = new Integrals();
        in.setCount(100);
        in.setUserId(989898L);
//        integralsService.toInsert();
        return R.ok();
    }

    @GetMapping("/toOrder1/{productId}/{currentUse}")
    public R toOrder1(@PathVariable Long productId, @PathVariable int currentUse) throws TransactionException {
        buisnessService.toOrder(productId,currentUse);
        return R.ok();
    }
}

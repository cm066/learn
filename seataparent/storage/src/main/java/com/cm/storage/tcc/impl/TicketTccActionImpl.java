package com.cm.storage.tcc.impl;

import com.cm.storage.entity.Ticket;
import com.cm.storage.mapper.TicketMapper;
import com.cm.storage.tcc.ResultHold;
import com.cm.storage.tcc.TicketTccAction;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Slf4j
public class TicketTccActionImpl implements TicketTccAction {

    @Autowired
    private TicketMapper ticketMapper;

    @Transactional
    @Override
    public boolean prepareDecreaseAccount( Long id, Integer frozens) {

        log.info("检查库存，并冻结，id=" + id + "， frozens=" + frozens);
        Ticket ticket = ticketMapper.selectById(id);
        if (ticket.getSurplus() <= 0) {
            throw new RuntimeException("没有可用的票了");
        }
        /*
        余票-frozens
        冻结+frozens
         */
        boolean b = ticketMapper.toReduceSurplus(id, frozens);

        //保存标识
//        ResultHold.setResult(getClass(), businessActionContext.getXid(), "p");
        return true;
    }

    @Transactional
    @Override
    public boolean commit(BusinessActionContext businessActionContext) {

        long id = Long.parseLong(businessActionContext.getActionContext("id").toString());
        Integer frozens = Integer.parseInt(businessActionContext.getActionContext("frozens").toString());
        log.info("减少冻结，增加易用，第二阶段，提交，d=" + id + "， frozens=" + frozens);
//        //防止重复提交
//        if (ResultHold.getResult(getClass(), businessActionContext.getXid()) == null) {
//            return true;
//        }
        boolean b = ticketMapper.addUse(id, frozens);
        return true;

    }
    @Transactional
    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {

        long id = Long.parseLong(businessActionContext.getActionContext("id").toString());
        Integer frozens = Integer.parseInt(businessActionContext.getActionContext("frozens").toString());
        log.info("ticket服务 回滚事务");
//        //防止重复提交
//        if (ResultHold.getResult(getClass(), businessActionContext.getXid()) == null) {
//            return true;
//        }
        boolean b = ticketMapper.addSur(id, frozens);
        return true;
    }
}

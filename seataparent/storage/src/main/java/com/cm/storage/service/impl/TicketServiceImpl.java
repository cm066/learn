package com.cm.storage.service.impl;

import com.cm.storage.service.TicketService;
import com.cm.storage.tcc.TicketTccAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketTccAction ticketTccAction;

    @Override
    public boolean reduceTicket(Long id, Integer frozens) {
        boolean b = ticketTccAction.prepareDecreaseAccount(id, frozens);

        return b;
    }
}

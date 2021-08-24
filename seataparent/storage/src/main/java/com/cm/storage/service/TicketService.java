package com.cm.storage.service;

public interface TicketService {

    boolean reduceTicket(Long id, Integer frozens);
}

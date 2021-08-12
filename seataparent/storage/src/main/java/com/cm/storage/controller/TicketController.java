package com.cm.storage.controller;

import com.cm.storage.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/toTicket/{id}/{frozens}")
    public boolean toTicket(@PathVariable Long id, @PathVariable Integer frozens){
        boolean b = ticketService.reduceTicket(id, frozens);
        return b;
    }
}

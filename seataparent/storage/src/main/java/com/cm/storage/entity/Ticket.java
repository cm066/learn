package com.cm.storage.entity;

import lombok.Data;

@Data
public class Ticket {
    private Long id;
    private Integer ticketNum;
    private Integer frozen;
    private Integer uses;
    private Integer surplus;
}

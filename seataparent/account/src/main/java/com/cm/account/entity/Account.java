package com.cm.account.entity;


import lombok.Data;

@Data
public class Account {
    private Long id;
    private Integer balance;
    private Integer frozen;
    private Long userId;
    private Integer useMoney;
}

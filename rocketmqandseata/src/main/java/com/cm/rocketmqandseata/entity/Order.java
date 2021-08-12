package com.cm.rocketmqandseata.entity;


import lombok.Data;

@Data
public class Order {

    private String oid;
    private String name;
    private String createTime;
    private Integer status;
}

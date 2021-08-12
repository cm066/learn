package com.cm.order.entity;



import lombok.Data;


@Data

public class Order {

    private Long id;
    private Long userId;
    private Long productId;
    private int count;
    private Integer status;
}

package com.cm.order.entity;

import lombok.Data;

@Data
public class Orders {
    private Long id;
    private Long productId;
    private Integer status;
    private Long UserId;
    private Integer productNum;
    private Integer money;
}

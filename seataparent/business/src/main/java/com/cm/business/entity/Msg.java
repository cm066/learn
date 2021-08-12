package com.cm.business.entity;

import lombok.Data;

@Data
public class Msg {
    private Long productId;
    private Integer currentUse;
    private String uuid;//唯一标识，防止消费者重复消费

    @Override
    public String toString() {
        return "Msg{" +
                "productId=" + productId +
                ", currentUse=" + currentUse +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}

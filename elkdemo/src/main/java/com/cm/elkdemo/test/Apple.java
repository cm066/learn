package com.cm.elkdemo.test;

import java.math.BigDecimal;

public class Apple implements Fruit{
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

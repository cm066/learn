package com.lufei.producer.bean;

import lombok.Data;

@Data
public class Product {
    String pname;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}

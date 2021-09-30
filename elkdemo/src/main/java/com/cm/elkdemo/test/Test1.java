package com.cm.elkdemo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.math.BigDecimal;

public class Test1 {
    public static void main(String[] args) {
        Store store = new Store();

        store.setName("Hollis");

        Apple apple = new Apple();

        apple.setPrice(new BigDecimal(0.5));

        store.setFruit(apple);

        String jsonString = JSON.toJSONString(store, SerializerFeature.WriteClassName);

        System.out.println("toJSONString : " + jsonString);
    }
}

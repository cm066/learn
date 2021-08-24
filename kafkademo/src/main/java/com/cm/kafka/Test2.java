package com.cm.kafka;

import java.util.ArrayList;
import java.util.HashMap;

public class Test2 {
    public static void main(String[] args) {
        System.out.println(-1L ^ (-1L << 12L));

        SnowFlake snowFlake = new SnowFlake(10L, 5L);
        for (int i = 0; i < 10; i++) {
            System.out.println(snowFlake.nextId());
        }

        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "1");

    }
}

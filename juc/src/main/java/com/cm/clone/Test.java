package com.cm.clone;

import java.util.*;

/**
 * @author Administrator
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Map<Long,ItemSold> returnMap = new HashMap<>();
        List<ItemSold> resultDb = getResultDb(new Date());
        for (ItemSold itemSold : resultDb) {
            ItemSold clone = (ItemSold) itemSold.clone();
            System.out.println(clone.getSkuSolds());
            System.out.println(itemSold.getSkuSolds());
            System.out.println(clone.getItemId());
            System.out.println(itemSold.getItemId());
//            System.out.println();
            returnMap.put(itemSold.getItemId(), clone);
        }
    }
    public static List<ItemSold> getResultDb(Date updateTime){
        List<ItemSold> result = new ArrayList<>();
        ItemSold itemSold = new ItemSold();
        itemSold.setSold(1L);
        itemSold.setItemId(1L);
        itemSold.setSkuSolds(Collections.singletonList(new SkuSold(1L,1L)));
        result.add(itemSold);
        return result;
    }
}

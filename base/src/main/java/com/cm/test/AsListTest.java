package com.cm.test;

import java.util.ArrayList;
import java.util.List;

public class AsListTest {
    public static void main(String[] args) {

        /**
         * 返回的是java.util.Arrays一个内部类，并不是java.util.ArrayList类，那个类并没有重写那些修改的方法
         * 所以会抛出异常
         * List<Integer> statusList = Arrays.asList(1, 2);
         *         System.out.println(statusList);
         *         System.out.println(statusList.contains(1));
         *         System.out.println(statusList.contains(3));
         *         String[] str = new String[]{"1","2","3"};
         *         List<String> strings = Arrays.asList(str);
         *         System.out.println(strings);
         *         str[0] = "2";
         *         System.out.println(strings);
         */

        /**
         * bookList.subList 返回的是ArrayList的一个内部类
         * private static class SubList<E> extends AbstractList<E> implements RandomAccess
         * 修改原集合元素的值，会影响子集合
         * 修改原集合的结构，会引起ConcurrentModificationException异常 因为里面并没有重写修改的方法
         * 修改子集合元素的值，会影响原集合
         * 修改子集合的结构，会影响原集合
         */
        List<String> bookList = new ArrayList<>();
        bookList.add("遥远的救世主");
        bookList.add("背叛");
        bookList.add("天幕红尘");
        bookList.add("人生");
        bookList.add("平凡的世界");

        List<String> luyaoBookList = bookList.subList(3, 5);

        System.out.println(bookList);
        System.out.println(luyaoBookList);

        bookList.remove(1);
        System.out.println(bookList);
//        System.out.println(luyaoBookList);
    }
}

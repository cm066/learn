package com.cm.proxy.cglib;

/**
 * todo 可以实现多个，加上责任链的模式来实现
 */
public class TestCglib {
    public static void main(String[] args) {
        UserServiceCglib cglib = new UserServiceCglib();
        UserServiceImpl proxy = (UserServiceImpl) cglib.getInstance(new UserServiceImpl());
        proxy.addUser();
    }
}

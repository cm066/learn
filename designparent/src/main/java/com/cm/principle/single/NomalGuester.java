package com.cm.principle.single;

public class NomalGuester implements IVedivUserService{
    @Override
    public void definition() {
        System.out.println("普通用户，清晰度为480");
    }

    @Override
    public void advertisement() {
        System.out.println("普通用户，有广告");
    }
}

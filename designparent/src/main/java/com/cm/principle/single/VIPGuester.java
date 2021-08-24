package com.cm.principle.single;

public class VIPGuester implements IVedivUserService {
    @Override
    public void definition() {
        System.out.println("VIP客户清晰度1080P");
    }

    @Override
    public void advertisement() {
        System.out.println("VIP客户，没有广告");
    }
}

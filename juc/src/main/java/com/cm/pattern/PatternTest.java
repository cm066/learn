package com.cm.pattern;

import java.util.regex.Pattern;

/**
 * @author Administrator
 */
public class PatternTest {
    //数字或者是字母^代表开头，+表示可以一次或者多次匹配前面的正则
    public static final String numOrStr = "^[A-za-z0-9]+$";
    //匹配身份证号码。开头一位只能是在1-9里面
    public static final String IDCARD = "^[1-9]([0-9]{14}|[0-9]{17})$";
    //字母和数组的组合
    public static final String allstring = "^\\w+$";

    public static final String userName = "^[A-Za-z]";
    public static void main(String[] args) {
        System.out.println(Pattern.matches(numOrStr,"122abc"));
        System.out.println(Pattern.matches(allstring,"123111bbbb"));
    }
}

package com.cm.proxy.proxy0;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        String s = "abcdabcdef";
        System.out.println(getlengthOfLongestSubstring(s));
    }
    public static int getlengthOfLongestSubstring(String str){
        int n = str.length();
        if (n <= 0){
            return 0;
        }
        Set<Character> occ = new HashSet<>();
        int rk =-1,ans = 0;
        for (int i = 0; i < n; i++) {
            if (i !=0){
                occ.remove(str.charAt(i-1));
            }
            while (rk+1 < n && !occ.contains(str.charAt(rk+1))){
                occ.add(str.charAt(rk+1));
                rk++;
            }
            ans = Math.max(ans,rk -i+1);
        }

        return ans;
    }
}

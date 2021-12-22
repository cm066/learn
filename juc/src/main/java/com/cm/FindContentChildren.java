package com.cm;

import java.util.Arrays;

/**
 * @author Administrator
 */
public class FindContentChildren {
    public static void main(String[] args) {

    }
    public static int findContentChildren(int[] g, int[] s) {
        int sum  = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int gl = g.length-1;
        int sl = s.length -1;
        int gi = 0;
        int si = 0;
        while (gi <= gl && si <= sl){
            if (g[gi] <= s[si]){
                sum++;
                gi++;
            }
            si++;
        }
        return sum;
    }
}

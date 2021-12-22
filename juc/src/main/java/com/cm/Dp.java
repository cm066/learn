package com.cm;

/**
 * @author Administrator
 * 动态规划，通用解体模板
 */
public class Dp {
    public static void main(String[] args) {
        int[] nums = {0,1,0,3,2,3};
        System.out.println(lengthOfLis(nums));
    }
    public static int lengthOfLis(int[] nums){
        if (nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxAns = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
               if (nums[j] < nums[i]){
                   dp[i] = Math.max(dp[i],dp[j]+1);
               }
            }
            maxAns = Math.max(maxAns,dp[i]);
        }
        return maxAns;
    }
}

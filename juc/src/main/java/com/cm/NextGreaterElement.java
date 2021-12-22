package com.cm;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {

    public static int[] nextGreaterElement(int[] nums){
        int[] ans = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length-1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]){
                stack.pop();
            }
            ans[i] = stack.empty() ? -1 :stack.peek();
            stack.push(nums[i]);
        }
        return ans;
    }

    public static int[] dailyTemperatures(int[] nums){
        int[] ans = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? 0:stack.peek() - i;
            stack.push(i);
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = {2,1,2,4,3};
        int[] ints = nextGreaterElement(nums);
        System.out.println(Arrays.toString(ints));
        int[] ints1 = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] ints2 = dailyTemperatures(ints1);
        System.out.println(Arrays.toString(ints2));
    }
}

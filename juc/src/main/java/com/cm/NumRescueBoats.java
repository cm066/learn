package com.cm;

import java.util.Arrays;

/**
 * @author Administrator
 */
public class NumRescueBoats {

    public static void main(String[] args) {
        int[] num = {5,1,7,2,8,9,4};
        numRescueBoats(num,9);
    }

    /**
     * 因为每次最多在limit的限制下乘坐两个人，所以每次都让最重和最亲的在一起，看是否超过了限制
     * @param people
     * @param limit
     * @return
     */
    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0;
        int right = people.length - 1;
        int count = 0;
        while (left <= right){
            if ((people[left]+people[right]) < limit){
                left++;
            }
            right--;
            count++;
        }
        return count;
    }
}

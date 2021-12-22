package com.cm;

/**
 * @author Administrator
 */
public class MySqrt {
    public static void main(String[] args) {
        System.out.println(mySqrt(8));
    }
    public static int mySqrt(int x) {

        if (x < 1){
            return 0;
        }
        int low = 1;
        int high = x;
        while (low <= high){
            int mid = (high - low) / 2 +low;
            if (x / mid == mid){
                return mid;
            }
            if (x / mid > mid){
                low = mid + 1;
            }
            if (x / mid < mid){
                high = mid - 1;
            }
        }
        return high;
    }
}

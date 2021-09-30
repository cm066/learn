/**
 * 利用位运算来解决
 */
public class HammingWeight {
    public static void main(String[] args) {
        System.out.println(hammingWeight(1));
    }
    public static int hammingWeight(int n){
        int res = 0;
        while (n != 0){
            n = n & (n-1);
            res++;
        }
        return res;
    }
    public static void exchange(int a ,int b){
        a ^= b; //或操作，相同即为0，不相同即为1
        b ^= a;
        a ^=b;
    }
}

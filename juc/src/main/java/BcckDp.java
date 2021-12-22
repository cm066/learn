/**
 * @author Administrator
 * 背包问题
 */
public class BcckDp {
    public static void main(String[] args) {

    }
    public static int packageProblem1() {
        int packageContainWeight = 4;//包最大可装重量
        int[] weight = {1, 4, 3};//3个物品的重量
        int[] value = {150, 300, 200};//3个物品的价值
        int[][] dp = new int[weight.length+1][value.length+1];
        for (int i = 1; i <= weight.length; i++) {
            for (int j = 1; j <= packageContainWeight; j++) {

                if (j >= weight[j-1]){
                    //这里就是分两种情况，一种情况就是要当前的，另外一种是不要当前的
//                    dp[i][j] = Math.max(dp[])
                }else {
                    //
                }

            }
        }
        return 0;
    }
}

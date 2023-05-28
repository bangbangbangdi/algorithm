package my_practise.p2023.basic.algorithm.dynamicProgramming.leftToRight;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.algorithm.dynamicProgramming.leftToRight
 * className:      CoinsWay
 * author:     BangDi
 * description:
 * // 给定数组arr,arr中所有的值都为正数且不重复
 * // 每个值代表一种面值的货币,每种面值的货币可以使用任意张
 * // 再给定一个正数aim,代表要找的钱数
 * // 求组成aim的方法数
 * date:    2023/5/28 10:32
 * version:    1.0
 */
public class CoinsWay {

    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int aim) {
        if (aim == 0) {
            return 1;
        }
        if (aim < 0 || index >= arr.length) {
            return 0;
        }
        return process(arr, index, aim - arr[index]) + process(arr, index + 1, aim);
    }

    public static int coinsWayDp(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 1; j <= aim; j++) {
                int p1 = j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
                dp[i][j] = p1 + dp[i+1][j];
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1};
        int aim = 350;
//        int[] arr = {1, 5, 10};
//        int aim = 15;
        System.out.println(coinsWay(arr, aim));
        System.out.println(coinsWayDp(arr, aim));
    }

}

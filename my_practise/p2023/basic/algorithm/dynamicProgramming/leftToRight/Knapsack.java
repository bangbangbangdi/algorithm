package my_practise.p2023.basic.algorithm.dynamicProgramming.leftToRight;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.algorithm.dynamicProgramming.leftToRight
 * className:      Knapsack
 * author:     BangDi
 * description:  TODO
 * // 给定两个长度都为N的数组weights和values
 * // weights[i]和values[i]分别表示i号物品的重量和价值
 * // 给你一个正数bag表示载重为bag的袋子
 * // 要装的商品不能超过该重量,请返回能装下的最大价值
 * date:    2023/5/28 21:10
 * version:    1.0
 */
public class Knapsack {

    public static int knapsack(int[] w, int[] v, int b) {
        if (w == null || v == null || w.length < 1 || w.length != v.length || b < 0) {
            return 0;
        }
        return process(w, v, b, 0);
    }

    public static int process(int[] w, int[] v, int b, int i) {
        if (b < 0) {
            return Integer.MIN_VALUE;
        }
        if (i == w.length) {
            return 0;
        }
        int r1 = process(w, v, b, i + 1);
        int r2 = process(w, v, b - w[i], i + 1) + v[i];
        return Math.max(r1, r2);
    }

    public static int knapsackDp(int[] w, int[] v, int b) {
        int N = w.length;
        int[][] dp = new int[N + 1][b + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= b; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - w[i] >= 0) {
                    dp[i][j] = Math.max(dp[i + 1][j - w[i]] + v[i], dp[i][j]);
                }
            }
        }
        return dp[0][b];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(knapsack(weights, values, bag));
        System.out.println(knapsackDp(weights, values, bag));
    }
}

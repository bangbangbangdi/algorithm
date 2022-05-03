package myClass12;

public class Code07_Knapsack {

    public static int getMaxValue(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length != values.length) {
            return 0;
        }
        return process(weights, values, 0, bag);
    }

    public static int process(int[] weights, int[] values, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index >= weights.length) {
            return 0;
        }
        int no = process(weights, values, index + 1, rest);
        int yes = Integer.MIN_VALUE;
        if (rest >= weights[index]) {
            yes = values[index] + process(weights, values, index + 1, rest - weights[index]);
        }
        return Math.max(yes, no);
    }


    public static int dpWay(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length) {
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int i = w.length - 1; i >= 0; i--) {
            for (int j = 0; j <= bag; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j > w[i] && i + 1 <= N) {
                    dp[i][j] = Math.max(dp[i][j],v[i] + dp[i + 1][j - w[i]]);
                }
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(getMaxValue(weights, values, bag));
        System.out.println(dpWay(weights, values, bag));
    }

}
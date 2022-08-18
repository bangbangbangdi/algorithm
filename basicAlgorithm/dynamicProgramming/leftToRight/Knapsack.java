package basicAlgorithm.dynamicProgramming.leftToRight;

// 给定两个长度都为N的数组weights和values
// weights[i]和values[i]分别表示i号物品的重量和价值
// 给你一个正数bag表示载重为bag的袋子
// 要装的商品不能超过该重量,请返回能装下的最大价值
public class Knapsack {

    public static int getMaxValue(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length != values.length || weights.length < 1 || bag < 0) {
            return 0;
        }
        return process(weights, values, bag, 0);
    }

    public static int process(int[] weights, int[] values, int rest, int index) {
        if (rest < 0) {
            return 0;
        }
        if (index == weights.length) {
            return 0;
        }

        int res1 = process(weights, values, rest, index + 1);
        int res2 = Integer.MIN_VALUE;
        if (rest >= weights[index]) {
            res2 = values[index] + process(weights, values, rest - weights[index], index + 1);
        }
        return Math.max(res1, res2);
    }

    public static int getMaxValue2(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length != values.length || weights.length < 1 || bag < 0) {
            return 0;
        }
        return dpWays(weights, values, bag, 0);
    }


    public static int dpWays(int[] w, int[] v, int rest, int index) {
        int N = w.length;
        int[][] arr = new int[N + 1][rest+1];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = rest; j >= 0; j--) {
                int res1 = arr[i + 1][j];
                int res2 = Integer.MIN_VALUE;
                if (j >= w[i]) {
                    res2 = v[i] + arr[i + 1][j - w[i]];
                }
                arr[i][j] = Math.max(res1, res2);
            }
        }
        return arr[0][rest];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(getMaxValue(weights, values, bag));
        System.out.println(getMaxValue2(weights, values, bag));
    }

}

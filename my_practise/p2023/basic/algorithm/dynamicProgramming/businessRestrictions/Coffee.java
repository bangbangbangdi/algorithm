package my_practise.p2023.basic.algorithm.dynamicProgramming.businessRestrictions;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.algorithm.dynamicProgramming.businessRestrictions
 * className:      Coffee
 * author:     BangDi
 * description:  TODO
 * date:    2023/5/27 00:52
 * version:    1.0
 */
// 给定一个数组,代表每个人喝完咖啡准备刷杯子的时间 从小到大
// 只有一台咖啡机,一次只能洗一个杯子,耗费时间a,洗完才能洗下一杯
// 每个咖啡机也可以自己挥发干净,时间耗费b,咖啡杯可以并行挥发
// 返回让所有咖啡杯变干净的最早完成时间
// int[] arr int a int b
public class Coffee {

    public static int coffee(int[] arr, int a, int b) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        if (b <= a) {
            return arr[arr.length - 1] + b;
        }
        return process(arr, a, b, 0, 0);
    }

    public static int process(int[] arr, int a, int b, int index, int availableTime) {
        if (index == arr.length - 1) {
            return Math.min(Math.max(arr[arr.length - 1], availableTime) + a, arr[arr.length - 1] + b);
        }
        int nextAvailable = Math.max(availableTime, arr[index]) + a;
        int f1 = Math.max(nextAvailable, process(arr, a, b, index + 1, nextAvailable));
        int f2 = Math.max(arr[index] + b, process(arr, a, b, index + 1, availableTime));
        return Math.min(f1, f2);
    }

    public static int coffeeDp(int[] arr, int a, int b) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        if (b <= a) {
            return arr[arr.length - 1] + b;
        }
        int N = arr.length;
        int limit = 0;
        for (int i : arr) {
            limit = Math.max(limit, i) + a;
        }
        int[][] dp = new int[N][limit + 1];
        for (int i = 0; i <= limit; i++) {
            dp[N - 1][i] = Math.min(Math.max(arr[N - 1], i) + a, arr[N - 1] + b);
        }
        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j <= limit; j++) {
                int f1 = Integer.MAX_VALUE;
                int nextAvailable = Math.max(j, arr[i]) + a;
                if (nextAvailable <= limit) {
                    f1 = Math.max(nextAvailable, dp[i + 1][nextAvailable]);
                }
                int f2 = Math.max(arr[i] + b, dp[i + 1][j]);
                dp[i][j] = Math.min(f1, f2);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 6, 6, 6, 6, 7, 10, 11, 11, 12};
        int a = 3;
        int b = 5;
        System.out.println(coffee(arr, a, b));
        System.out.println(coffeeDp(arr, a, b));
    }

}

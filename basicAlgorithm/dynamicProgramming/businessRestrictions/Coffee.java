package basicAlgorithm.dynamicProgramming.businessRestrictions;

// 给定一个数组,代表每个人喝完咖啡准备刷杯子的时间 从小到大
// 只有一台咖啡机,一次只能洗一个杯子,耗费时间a,洗碗才能洗下一杯
// 每个咖啡机也可以自己挥发干净,时间耗费b,咖啡杯可以并行挥发
// 返回让所有咖啡杯变干净的最早完成时间
// int[] arr int a int b
public class Coffee {

    public static int coffee(int[] arr, int a, int b) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        if (a >= b) {
            return arr[arr.length - 1] + b;
        }
        return process(arr, a, b, 0, 0);
    }

    public static int process(int[] arr, int a, int b, int index, int available) {
        if (index == arr.length - 1) {
            return Math.min(Math.max(arr[index], available) + a, arr[index] + b);
        }
        int nextAvailable = Math.max(arr[index], available) + a;
        int nextFinish1 = process(arr, a, b, index + 1, nextAvailable);
        int p1 = Math.max(nextAvailable, nextFinish1);

        int dry = arr[index] + b;
        int nextFinish2 = process(arr, a, b, index + 1, available);
        int p2 = Math.max(dry, nextFinish2);
        return Math.min(p1, p2);
    }

    public static int coffee2(int[] arr, int a, int b) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        if (a >= b) {
            return arr[arr.length - 1] + b;
        }
        int N = arr.length - 1;
        int limit = 0;
        for (int j : arr) {
            limit = Math.max(j, limit) + a;
        }
        int[][] dp = new int[N][limit];
        for (int i = 0; i < limit; i++) {
            dp[N - 1][i] = Math.min(Math.max(arr[N], limit) + a, arr[N] + b);
        }

        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j < limit; j++) {
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 6, 6, 6, 6, 10, 12, 13, 14, 18, 19};
        int a = 3;
        int b = 5;
        System.out.println(coffee(arr, a, b));
    }
}

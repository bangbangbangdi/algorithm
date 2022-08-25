package basicAlgorithm.dynamicProgramming.businessRestrictions;

// 给定一个数组,代表每个人喝完咖啡准备刷杯子的时间 从小到大
// 只有一台咖啡机,一次只能洗一个杯子,耗费时间a,洗碗才能洗下一杯
// 每个咖啡机也可以自己挥发干净,时间耗费b,咖啡杯可以并行挥发
// 返回让所有咖啡杯变干净的最早完成时间
// int[] arr int a int b
public class Coffee {

    public static int coffee1(int[] arr, int a, int b) {
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
            return Math.min(arr[index] + b, Math.max(available, arr[index]) + a);
        }
        int nextAvailable = Math.max(available, arr[index]) + a;
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
        int N = arr.length;
        int limit = 0;
        for (int i = 0; i < N; i++) {
            limit = Math.max(limit, arr[i]) + a;
        }
        int[][] dp = new int[N][limit + 1];
        for (int available = 0; available <= limit; available++) {
            dp[N - 1][available] = Math.min(arr[N - 1] + b, Math.max(available, arr[N - 1]) + a);
        }
        for (int index = N - 2; index >= 0; index--) {
            for (int available = 0; available <= limit; available++) {
                int nextAvailable = Math.max(available, arr[index]) + a;
                int p1 = Integer.MAX_VALUE;
                if (nextAvailable <= limit) {
                    p1 = Math.max(dp[index + 1][nextAvailable], nextAvailable);
                }
                int dry = arr[index] + b;
                int nextFinish = dp[index + 1][available];
                int p2 = Math.max(dry, nextFinish);
                dp[index][available] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }


    public static void main(String[] args) {
        int[] arr = {1, 5, 6,6,6,6,7,10,11,11,12};
        int a = 3;
        int b = 5;
        System.out.println(coffee1(arr, a, b));
        System.out.println(coffee2(arr, a, b));
    }

}

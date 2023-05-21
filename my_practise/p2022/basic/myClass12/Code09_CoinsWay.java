package my_practise.p2022.basic.myClass12;

public class Code09_CoinsWay {

    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process2(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int aim) {
        if (index == arr.length) {
            return aim == 0 ? 1 : 0;
        }
        int res = 0;
        while (aim >= 0) {
            res += process(arr, index + 1, aim);
            aim -= arr[index];
        }
        return res;
    }

    public static int process2(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int way = 0;
        for (int num = 0; num * arr[index] <= rest; num++) {
            way += process(arr, index + 1, rest - num * arr[index]);
        }
        return way;
    }

    public static int dpWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - arr[i] >= 0) {
                    dp[i][j] += dp[i][j - arr[i]];
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1};
        int sum = 350;
        System.out.println(coinsWay(arr, sum));
        System.out.println(dpWays(arr, sum));
    }

}

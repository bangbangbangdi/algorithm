package basicAlgorithm.dynamicProgramming.leftToRight;

// 给定数组arr,arr中所有的值都为正数且不重复
// 每个值代表一种面值的货币,每种面值的货币可以使用任意张
// 再给定一个正数aim,代表要找的钱数
// 求组成aim的方法数
public class CoinsWay {

    public static int coins(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 1) {
            return 0;
        }
        return process(arr, aim, 0);
    }

    public static int process(int[] arr, int rest, int index) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ans = 0;
        for (int i = 0; arr[index] * i <= rest; i++) {
            ans += process(arr, rest - arr[index] * i, index + 1);
        }
        return ans;
    }

    public static int dpWays(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 1) {
            return 0;
        }
        int[][] dp = new int[aim + 1][arr.length + 1];
        dp[0][arr.length] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[rest][index] = dp[rest][index + 1];
                if (rest - arr[index] >= 0) {
                    dp[rest][index] += dp[rest - arr[index]][index];
                }
            }
        }
        return dp[aim][0];
    }


    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1};
        int sum = 350;

        System.out.println(coins(arr, sum));
        System.out.println(dpWays(arr, sum));
    }

}
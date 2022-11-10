package my_practise.basic.myClass12;

import java.util.Arrays;

public class Code06_ConvertToLetterString {
    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(0, str.toCharArray());
    }

    public static int process(int index, char[] chars) {
        if (index == chars.length) {
            return 1;
        }
        if (index > chars.length || chars[index] == '0') {
            return 0;
        }

        if (chars[index] == '1') {
            int i = process(index + 1, chars);
            int j = process(index + 2, chars);
            return i + j;
        }

        if (chars[index] == '2') {
            int i = process(index + 1, chars);
            int j = 0;
            if (chars[index + 1] - 48 < 7) {
                j = process(index + 2, chars);
            }
            return i + j;
        }
        return process(index + 1, chars);
    }

    // 傻缓存方式实现记忆化搜索
    public static int number2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int[] dp = new int[str.length() + 2];
        Arrays.fill(dp, -1);
        return dpWays(0, str.toCharArray(), dp);
    }

    public static int dpWays(int index, char[] chars, int[] dp) {
        if (index == chars.length) {
            dp[index] = 1;
            return dp[index];
        }
        if (index > chars.length || chars[index] == '0') {
            dp[index] = 0;
            return dp[index];
        }

        if (chars[index] == '1') {
            if (dp[index] != -1) {
                return dp[index];
            }
            int i = dpWays(index + 1, chars, dp);
            int j = dpWays(index + 2, chars, dp);
            dp[index] = i + j;
            return dp[index];
        }

        if (chars[index] == '2') {
            if (dp[index] != -1) {
                return dp[index];
            }
            int i = process(index + 1, chars);
            int j = 0;
            if (chars[index + 1] - 48 < 7) {
                j = process(index + 2, chars);
            }
            dp[index] = i + j;
            return dp[index];
        }
        return dp[index + 1];
    }

    public static int dpWays2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int N = chars.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;

        for (int i = N - 1; i >= 0; i--) {
            if (chars[i] == '0') {
                dp[i] = 0;
            } else if (chars[i] == '1') {
                dp[i] = dp[i + 1];
                if (i + 2 <= N) {
                    dp[i] += dp[i + 2];
                }
            } else if (chars[i] == '2') {
                dp[i] = dp[i + 1];
                if (i + 2 <= N && chars[i + 1] >= '0' && chars[i + 1] <= 6) {
                    dp[i] += dp[i + 2];
                }
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];

    }


    public static void main(String[] args) {
        String str = "11111";
        System.out.println(number(str));
        System.out.println(number2(str));
        System.out.println(dpWays2(str));
    }
}
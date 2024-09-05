package my_practise.p2024.pro.algorithm;

/**
 * projectName:    algorithm
 * package:        my_practise.p2024.pro.algorithm
 * className:      NeedParentheses
 * author:     BangDi
 * description:
 * 1.返回一个字符串中最长括号的有效子串长度
 * 2.返回一个字符串中有效括号的最大深度
 * date:    2024/4/30 19:21
 * version:    1.0
 */
public class Parentheses {
    public static int maxLength(String str) {
        if (str == null || str.length() < 2) {
            return 0;
        }
        int res = 0;
        int N = str.length();
        char[] chs = str.toCharArray();
        int[] dp = new int[N];
        int pre = 0;
        for (int i = 1; i < N; i++) {
            if (chs[i] == ')') {
                pre = i - 1 - dp[i - 1];
                if (pre >= 0 && chs[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    public static int maxDeep(String str) {
        if (str == null || str.length() < 2) {
            return 0;
        }
        int res = 0;
        char[] chs = str.toCharArray();
        int N = str.length();
        int[] dp = new int[N];
        int pre = 0;
        for (int i = 1; i < N; i++) {
            if (chs[i] == ')') {
                pre = i - 1 - dp[i - 1];
                if (pre >= 0 && chs[pre] == '(') {
                    dp[i] = dp[i - 1] + 2;
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "((()()";
        System.out.println(maxLength(str));
        System.out.println(maxDeep(str));
    }
}

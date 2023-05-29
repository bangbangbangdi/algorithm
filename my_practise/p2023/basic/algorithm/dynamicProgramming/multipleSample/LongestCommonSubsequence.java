package my_practise.p2023.basic.algorithm.dynamicProgramming.multipleSample;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.dynamicProgramming.multipleSample
 * className:      LongestCommonSubsequence
 * author:     BangDi
 * description:  最长公共子序列
 * date:    2023/5/29 13:20
 * version:    1.0
 */
// 返回两个字符串的最长公共子序列的长度
public class LongestCommonSubsequence {

    public static int longestCommonSubsequence(String s1, String s2) {
        if (s1 == null || s1.length() < 1 || s2 == null || s2.length() < 1) {
            return 0;
        }
        return process(s1.toCharArray(), s2.toCharArray(), 0, 0);
    }

    public static int process(char[] chs1, char[] chs2, int i1, int i2) {
        if (i1 == chs1.length - 1 && i2 == chs2.length - 1) {
            return chs1[i1] == chs2[i2] ? 1 : 0;
        }
        if (i1 == chs1.length - 1) {
            return chs1[i1] == chs2[i2] ? 1 : process(chs1, chs2, i1, i2 + 1);
        }
        if (i2 == chs2.length - 1) {
            return chs1[i1] == chs2[i2] ? 1 : process(chs1, chs2, i1 + 1, i2);
        }
        if (chs1[i1] == chs2[i2]) {
            return process(chs1, chs2, i1 + 1, i2 + 1) + 1;
        }
        return Math.max(process(chs1, chs2, i1 + 1, i2), process(chs1, chs2, i1, i2 + 1));
    }

    public static int dpWay(String s1, String s2) {
        if (s1 == null || s1.length() < 1 || s2 == null || s2.length() < 1) {
            return 0;
        }
        int N1 = s1.length();
        int N2 = s2.length();
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        int[][] dp = new int[N1][N2];
        dp[N1 - 1][N2 - 1] = chs1[N1 - 1] == chs2[N2 - 1] ? 1 : 0;
        for (int i = N1 - 2; i >= 0; i--) {
            dp[i][N2 - 1] = chs1[i] == chs2[N2 - 1] ? 1 : dp[i + 1][N2 - 1] == 1 ? 1 : 0;
        }
        for (int i = N2 - 2; i >= 0; i--) {
            dp[N1 - 1][i] = chs2[i] == chs1[N1 - 1] ? 1 : dp[N1 - 1][i + 1] == 1 ? 1 : 0;
        }
        for (int i = N1 - 2; i >= 0; i--) {
            for (int j = N2 - 2; j >= 0; j--) {
                if (chs1[i] == chs2[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }


    public static void main(String[] args) {
        String s1 = "abcd";
        String s2 = "aabbeagasderqwec";
        System.out.println(longestCommonSubsequence(s1, s2));
        System.out.println(dpWay(s1, s2));
    }

}

package my_practise.p2024.pro.algorithm;

import tool.Tools;

import java.util.Arrays;

/**
 * projectName:    algorithm
 * package:        my_practise.p2024.pro.algorithm
 * className:      PalindromeMinEndAdd
 * author:     BangDi
 * description:
 * 给定一个字符串,能在任意位置添加字符,最少添加几个能让字符串整体都是回文
 * review:
 * 一眼...,算了看不出来;范围上的尝试模型
 * 区别于从左到右的尝试模型一般只考虑开头或结尾的情况,范围上的尝试模型需要同时考虑开头和结尾;
 * date:    2024/7/17 18:51
 * version:    1.0
 */
public class PalindromeMinAnyAdd {

    public static int[][] getDp(String str) {
        int N = str.length();
        int[][] dp = new int[N][N];
        char[] chs = str.toCharArray();
        for (int i = 0; i < N - 1; i++) {
            dp[i][i + 1] = chs[i] == chs[i + 1] ? 0 : 1;
        }
        for (int e = 2; e < N; e++) {
            int curE = e;
            for (int s = 0; s < N - e; s++, curE++) {
                dp[s][curE] = Math.min(dp[s][curE - 1], dp[s + 1][curE]) + 1;
                dp[s][curE] = chs[s] == chs[curE] ? Math.min(dp[s + 1][e - 1], dp[s][curE]) : dp[s][curE];
            }
        }
//        Tools.printMatrix(dp);
        return dp;
    }

    public static int palindromeMinAnyAdd(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int N = str.length();
        int[][] dp = getDp(str);
        char[] chs = str.toCharArray();
        for (int i = 0; i < N - 1; i++) {
            dp[i][i + 1] = chs[i] == chs[i + 1] ? 0 : 1;
        }
        for (int e = 2; e < N; e++) {
            int curE = e;
            for (int s = 0; s < N - e; s++, curE++) {
                dp[s][curE] = Math.min(dp[s][curE - 1], dp[s + 1][curE]) + 1;
                dp[s][curE] = chs[s] == chs[curE] ? Math.min(dp[s + 1][e - 1], dp[s][curE]) : dp[s][curE];
            }
        }
//        Tools.printMatrix(dp);
        return dp[0][N - 1];
    }

    public static String palindromeMin(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        int N = str.length();
        int[][] dp = getDp(str);
        char[] chs = str.toCharArray();
        char[] tmp = new char[dp[0][N - 1] + N];
        int s = 0;
        int e = N - 1;
        int i = 0;
        while (s < e) {
            // 经验:如果三元运算符的结果在后续还会用到的话,就别搞这些花里胡哨的,老老实实用if
            char val = dp[s + 1][e] > dp[s][e - 1] ? chs[e] : chs[s];

            int offsetS = (chs[s] == chs[e] && dp[s + 1][e - 1] < dp[s][e - 1]) || dp[s + 1][e] <= dp[s][e - 1] ? 1 : 0;
            int offsetE = (chs[s] == chs[e] && dp[s + 1][e - 1] < dp[s + 1][e]) || dp[s + 1][e] > dp[s][e - 1] ? 1 : 0;

            s += offsetS;
            e -= offsetE;
            tmp[i] = val;
            tmp[tmp.length - i - 1] = val;
            i++;
        }
        tmp[i] = chs[s];
        tmp[tmp.length - i - 1] = chs[e];

        String res = String.valueOf(tmp);
        return res;
    }

    public static String palindromeMin2(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        int N = str.length();
        int[][] dp = getDp(str);
        char[] chs = str.toCharArray();
        char[] tmp = new char[dp[0][N - 1] + N];
        int s = 0;
        int e = N - 1;
        int fontI = 0;
        int backI = tmp.length - 1;
        while (s <= e) {
            if (chs[s] == chs[e]) {
                tmp[fontI++] = chs[s++];
                tmp[backI--] = chs[e--];
            } else if (dp[s + 1][e] < dp[s][e - 1]) {
                tmp[fontI++] = chs[s];
                tmp[backI--] = chs[s++];
            } else {
                tmp[fontI++] = chs[e];
                tmp[backI--] = chs[e--];
            }
        }

        String res = String.valueOf(tmp);
        return res;
    }

    public static void main(String[] args) {
        String str = "32123axa";
        int res = palindromeMinAnyAdd(str);
        String s = palindromeMin(str);
        String s2 = palindromeMin2(str);
        System.out.println("res = " + res);
        System.out.println("s = " + s);
        System.out.println("s2 = " + s2);
    }

}

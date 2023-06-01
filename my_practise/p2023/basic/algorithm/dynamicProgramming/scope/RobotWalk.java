package my_practise.p2023.basic.algorithm.dynamicProgramming.scope;

import java.util.Arrays;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.algorithm.dynamicProgramming.scope
 * className:      RobotWalk
 * author:     BangDi
 * description:
 * 假设有排成一行的N个位置,记为1~N,N一定大于或等于2
 * 开始时机器人在其中的M位置上(M一定是1~N中的一个)
 * 如果机器人来到1位置,那么下一步只能往右来到2位置
 * 如果机器人来到N位置,那么下一步只能往左来到N-1位置
 * 如果机器人来到中间位置,那么下一步可以往左或者往右
 * 规定机器人必须走K步,最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数N,M,K,P 返回方法数
 * date:    2023/6/1 17:11
 * version:    1.0
 */
public class RobotWalk {

    public static int robotWalk(int N, int M, int K, int P) {
        if (M > N || K < 0 || P > N || P < 1) {
            return 0;
        }
        return process(N, M, K, P);
    }

    public static int process(int N, int M, int K, int P) {
        if (K == 0) {
            return M == P ? 1 : 0;
        }
        if (M <= 0 || M > N) {
            return 0;
        }
        return process(N, M + 1, K - 1, P) + process(N, M - 1, K - 1, P);
    }

    public static int dpWays(int N, int M, int K, int P) {
        if (M > N || K < 0 || P > N || P < 1) {
            return 0;
        }
        int[][] dp = new int[N + 2][K + 1];
        dp[P][0] = 1;
        for (int k = 1; k <= K; k++) {
            for (int m = 1; m <= N; m++) {
                dp[m][k] = dp[m + 1][k - 1] + dp[m - 1][k - 1];
            }
        }
        return dp[M][K];
    }

    public static int dpWaysPro(int N, int M, int K, int P) {
        if (M > N || K < 0 || P > N || P < 1) {
            return 0;
        }
        int[] buffer = new int[N + 2];
        int[] dp = new int[N + 2];
        buffer[P] = 1;
        for (int k = 1; k <= K; k++) {
            Arrays.fill(dp,0);
            for (int m = 1; m <= N; m++) {
                dp[m] = buffer[m + 1] + buffer[m - 1];
            }
            System.arraycopy(dp, 0, buffer, 0, dp.length);
        }
        return dp[M];
    }

    public static void main(String[] args) {
        int N = 7;
        int M = 4;
        int K = 9;
        int P = 5;
        System.out.println(robotWalk(N, M, K, P));
        System.out.println(dpWays(N, M, K, P));
        System.out.println(dpWaysPro(N, M, K, P));
//        System.out.println(robotWalk3(N, M, K, P));
    }
}

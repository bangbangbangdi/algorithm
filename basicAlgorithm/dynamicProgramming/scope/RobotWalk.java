package basicAlgorithm.dynamicProgramming.scope;

// 假设有排成一行的N个位置,记为1~N,N一定大于或等于2
// 开始时机器人在其中的M位置上(M一定是1~N中的一个)
// 如果机器人来到1位置,那么下一步只能往右来到2位置
// 如果机器人来到N位置,那么下一步只能往左来到N-1位置
// 如果机器人来到中间位置,那么下一步可以往左或者往右
// 规定机器人必须走K步,最终能来到P位置(P也是1~N中的一个)的方法有多少种
// 给定四个参数N,M,K,P 返回方法数
public class RobotWalk {

    public static int robotWalk(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 0 || P < 1 || P > N) {
            return -1;
        }
        return process(N, M, K, P);
    }

    public static int process(int N, int M, int K, int P) {
        if (K == 0) {
            return M == P ? 1 : 0;
        }
        if (M == 1) {
            return process(N, M + 1, K - 1, P);
        }
        if (M == N) {
            return process(N, M - 1, K - 1, P);
        }
        return process(N, M + 1, K - 1, P) + process(N, M - 1, K - 1, P);
    }

    public static int robotWalk2(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 0 || P < 1 || P > N) {
            return -1;
        }
        int[][] dp = new int[K + 1][N + 1];
        // 初始化缓存
        dp[0][P] = 1;

        for (int k = 1; k <= K; k++) {
            for (int m = 1; m <= N; m++) {
                if (m == 1) {
                    dp[k][m] = dp[k - 1][m + 1];
                } else if (m == N) {
                    dp[k][m] = dp[k - 1][m - 1];
                } else {
                    dp[k][m] = dp[k - 1][m + 1] + dp[k - 1][m - 1];
                }
            }
        }
        return dp[K][M];
    }

    public static int robotWalk3(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 0 || P < 1 || P > N) {
            return -1;
        }
        int[] dp = new int[N + 1];
        dp[P] = 1;
        for (int k = 1; k <= K; k++) {
            int leftTop = dp[1];
            for (int m = 1; m <= N; m++) {
                int temp = dp[m];
                if (m == 1) {
                    dp[m] = dp[m + 1];
                } else if (m == N) {
                    dp[m] = leftTop;
                } else {
                    dp[m] = dp[m + 1] + leftTop;
                }
                leftTop = temp;
            }
        }
        return dp[M];
    }

    public static void main(String[] args) {
        int N = 7;
        int M = 4;
        int K = 9;
        int P = 5;
        System.out.println(robotWalk(N, M, K, P));
        System.out.println(robotWalk2(N, M, K, P));
        System.out.println(robotWalk3(N, M, K, P));
    }
}

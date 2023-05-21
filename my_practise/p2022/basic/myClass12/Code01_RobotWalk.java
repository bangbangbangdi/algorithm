package my_practise.p2022.basic.myClass12;

public class Code01_RobotWalk {

    public static int rw1(int M, int P, int K, int N) {
        return process(M, P, K, N);
    }

    public static int process(int M, int P, int K, int N) {
        if (K == 0) {
            if (M == P) {
                return 1;
            }
            return 0;
        }
        // 还得往下走

        if (M == 1) {
            return process(M + 1, P, K - 1, N);
        }
        if (M == N) {
            return process(M - 1, P, K - 1, N);
        }

        int toLeft = process(M - 1, P, K - 1, N);
        int toRight = process(M + 1, P, K - 1, N);

        return toLeft + toRight;
    }

    public static int dpWay(int M, int P, int K, int N) {

        int[][] dp = new int[N + 1][K+1];
        dp[P][0] = 1;

        for (int k = 1; k <= K; k++) {
            for (int m = 1; m <= N; m++) {
                if (m == 1) {
                    dp[m][k] = dp[m + 1][k - 1];
                } else if (m == N) {
                    dp[m][k] = dp[m - 1][k - 1];
                } else {
                    dp[m][k] = dp[m - 1][k - 1];
                    dp[m][k] += dp[m + 1][k - 1];
                }
            }
        }

        return dp[M][K];
    }


    public static void main(String[] args) {
        int M = 4;
        int P = 5;
        int K = 9;
        int N = 7;
        System.out.println(rw1(M, P, K, N));
        System.out.println(dpWay(M, P, K, N));
    }

}
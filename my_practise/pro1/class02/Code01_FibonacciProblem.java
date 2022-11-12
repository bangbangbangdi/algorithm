package my_practise.pro1.class02;

import tool.Tools;

import java.util.Arrays;

// 斐波那契额数列矩阵乘法方式实现
public class Code01_FibonacciProblem {

    public static int f1(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1 || N == 2) {
            return 1;
        }
        return f1(N - 1) + f1(N - 2);
    }

    public static int f2(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1 || N == 2) {
            return 1;
        }
        int[] help = new int[N];
        help[0] = 1;
        help[1] = 1;
        for (int i = 2; i < N; i++) {
            help[i] = help[i - 1] + help[i - 2];
        }
        return help[N - 1];
    }

    public static int f3(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1 || N == 2) {
            return 1;
        }
        int[][] basic = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(basic, N - 2);
        return res[0][0] + res[1][0];
    }

    public static int[][] multiMatrix(int[][] m1, int[][] m2) {
        if (m1 == null || m2 == null || m1[0].length != m2.length) {
            return null;
        }
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m1[0].length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    // 一个矩阵求乘积实际上就在要求矩阵本身是一个方阵
    public static int[][] matrixPower(int[][] m, int p) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return m;
        }
        // 由于矩阵是方阵,故这里不存在索引越界的情况
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = multiMatrix(res, tmp);
            }
            tmp = multiMatrix(tmp, tmp);
        }
        return res;
    }

    public static void test() {
        int testTime = 100000;
        int maxN = 20;
        boolean success = true;
        System.out.println("test begin");
        for (int i = 0; success && i < testTime; i++) {
            int N = (int) (Math.random() * maxN);
            int ans1 = f1(N);
            int ans2 = f2(N);
            int ans3 = f3(N);
            if (ans1 != ans2 || ans1 != ans3) {
                success = false;
                System.out.println("N = " + N);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                System.out.println("ans3 = " + ans3);
            }
        }
        System.out.println(success ? "Nice" : "F");
    }


    public static void main(String[] args) {
        test();
    }

}

package my_practise.p2023.pro.algorithm;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm
 * className:      FibonacciProblem
 * author:     BangDi
 * description:  TODO
 * 1.求斐波那契数列 要求时间复杂度是O(log(2,N))
 * date:    2023/7/4 19:37
 * version:    1.0
 */
public class FibonacciProblem {
    // 常规递归
    public static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    // 累加法
    public static int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int tmp = 0;
        int res = 1;
        int pre = 1;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res += pre;
            pre = tmp;
        }
        return res;
    }

    // 接下来是我们的主角时间复杂度为log(2,N)的解法
    public static int f3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }

    public static int[][] matrixPower(int[][] m, int p) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return null;
        }
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = m;
        while (p != 0) {
            if ((p & 1) != 0) {
                res = multiMatrix(res, tmp);
            }
            p >>= 1;
            tmp = multiMatrix(tmp, tmp);
        }
        return res;
    }

    public static int[][] multiMatrix(int[][] m1, int[][] m2) {
        if (m1 == null || m2 == null || m1.length == 0 || m1.length != m2[0].length) {
            return null;
        }
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[j][k];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(f1(20));
        System.out.println(f2(20));
        System.out.println(f3(20));
    }

}
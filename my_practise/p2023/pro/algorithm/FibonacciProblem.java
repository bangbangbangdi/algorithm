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
        if (m == null || m.length != m[0].length || p < 0) {
            return null;
        }
        int[][] res = new int[m.length][m[0].length];
        int[][] tmp = m;
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
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
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m1.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    //一个人可以一次往上迈1个台阶，也可以迈2个台阶
    //返回这个人迈上N级台阶的方法数
    public static int step1(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        if (N == 2) {
            return 2;
        }
        return step1(N - 1) + step1(N - 2);
    }


    public static int step2(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        if (N == 2) {
            return 2;
        }
        int tmp = 0;
        int res = 2;
        int pre = 1;
        while (N - 2 != 0) {
            tmp = res;
            res += pre;
            pre = tmp;
            N--;
        }
        return res;
    }

    public static int step3(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        if (N == 2) {
            return 2;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, N - 2);
        return 2 * res[0][0] + res[1][0];
    }


    //第一年农场有1只成熟的母牛A，往后的每年：
    //1）每一只成熟的母牛都会生一只母牛
    //2）每一只新出生的母牛都在出生的第三年成熟
    //3）每一只母牛永远不会死
    //返回N年后牛的数量
    public static int cow1(int N) {
        if (N < 1) {
            return 0;
        }
        if (N < 4) {
            return N;
        }
        return cow1(N - 1) + cow1(N - 3);
    }

    public static int cow2(int N) {
        if (N < 1) {
            return 0;
        }
        if (N < 4) {
            return N;
        }
        int tmp = 0;
        int pre1 = 1;
        int pre2 = 2;
        int res = 3;
        while (N - 3 != 0) {
            tmp = res;
            res += pre1;
            pre1 = pre2;
            pre2 = tmp;
            N--;
        }
        return res;
    }

    public static int cow3(int N) {
        if (N < 1) {
            return 0;
        }
        if (N < 4) {
            return N;
        }
//        int[][] base = {{1, 1, 0}, {0, 0, 1}, {1, 0, 0}};
//        int[][] res = matrixPower(base, N - 3);
        int[][] base = {{1, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        int[][] res = matrixPower(base, N - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }


    public static void main(String[] args) {
        System.out.println(f1(20));
        System.out.println(f2(20));
        System.out.println(f3(20));
        System.out.println("===============");
        System.out.println(step1(20));
        System.out.println(step2(20));
        System.out.println(step3(20));
        System.out.println("===============");
        System.out.println(cow1(20));
        System.out.println(cow2(20));
        System.out.println(cow3(20));
    }

}
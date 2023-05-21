package my_practise.p2022.pro1.class02;

/*
重要结论:
    任何递归式只要满足 : F(N) = C1*F(N-1) + C2*F(N-2) + ... + CkF(N-k)
    则一定有logN的解
    例如:F(N) = F(N-1) + F(N-3)  -->  C1 = 1  C2 = 0  C3 = 1  k = 3
    F(N)*F(N-1)*F(N-2) = |F3 F2 F1| * |3*3|^n-3
*/


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


    // 以下就是一些衍生题
    // 一个人一次可以往上迈一个台阶,也可以迈两个台阶
    // 返回这个人迈上N级台阶的方法数

    public static int s1(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        if (N == 2) {
            return 2;
        }
        return s1(N - 1) + s1(N - 2);
    }

    public static int s2(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        if (N == 2) {
            return 2;
        }
        int[] help = new int[N];
        help[0] = 1;
        help[1] = 2;
        for (int i = 2; i < help.length; i++) {
            help[i] = help[i - 1] + help[i - 2];
        }
        return help[N - 1];
    }

    public static int s3(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        if (N == 2) {
            return 2;
        }
        int[][] basic = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(basic, N - 2);
        return 2 * res[0][0] + res[1][0];
    }

    // 第一年农场有一只成熟的母牛A,往后的每年
    // 每一只成熟的母牛都会生一只母牛
    // 每一只新出生的母牛都在出生的第三年成熟
    // 每一只母牛永远不会死
    // 返回N年后牛的数量(公牛做插排去了)

    public static int c1(int N) {
        if (N < 1) {
            return 0;
        }
        if (N < 5) {
            return N;
        }
        return c1(N - 1) + c1(N - 3);
    }

    public static int c2(int N) {
        if (N < 1) {
            return 0;
        }
        if (N < 5) {
            return N;
        }
        int[] help = new int[N];
        for (int i = 0; i < 4; i++) {
            help[i] = i + 1;
        }
        for (int i = 4; i < N; i++) {
            help[i] = help[i - 1] + help[i - 3];
        }
        return help[N - 1];
    }

    public static int c3(int N) {
        if (N < 1) {
            return 0;
        }
        if (N < 5) {
            return N;
        }
        int[][] basic = {{1, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        int[][] res = matrixPower(basic, N - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
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
            int s1 = s1(N);
            int s2 = s2(N);
            int s3 = s3(N);
            int c1 = c1(N);
            int c2 = c2(N);
            int c3 = c3(N);
            if (ans1 != ans2 || ans1 != ans3) {
                success = false;
                System.out.println("N = " + N);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                System.out.println("ans3 = " + ans3);
            }
            if (s1 != s2 || s1 != s3) {
                success = false;
                System.out.println("N = " + N);
                System.out.println("s1 = " + s1);
                System.out.println("s2 = " + s2);
                System.out.println("s3 = " + s3);
            }
            if (c1 != c2 || c1 != c3) {
                success = false;
                System.out.println("N = " + N);
                System.out.println("c1 = " + c1);
                System.out.println("c2 = " + c2);
                System.out.println("c3 = " + c3);
            }
        }
        System.out.println(success ? "Nice" : "F");
    }


    public static void main(String[] args) {
        test();
    }

}

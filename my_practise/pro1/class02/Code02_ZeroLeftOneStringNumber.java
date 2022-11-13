package my_practise.pro1.class02;


// 给定一个数N,想象只由0和1两种字符,组成的所有长度为N的字符串
// 如果某个字符串,任何0字符的左边都有一个1紧挨着,认为这个字符串达标
// 返回有多少达标的字符串
public class Code02_ZeroLeftOneStringNumber {

    // 实际上这个就是一个简单的斐波那契求解
    // F1 = 1
    // F2 = 2
    // F3 = 3
    // F4 = 5
    // F(N) = F(N-1) + F(N-2)
    public static int getNum1(int N) {
        if (N < 1) {
            return 0;
        }
        if (N <= 2) {
            return N;
        }
        return getNum1(N - 1) + getNum1(N - 2);
    }

    public static int getNum2(int N) {
        if (N < 1) {
            return 0;
        }
        if (N <= 2) {
            return N;
        }
        int[][] basic = {{1, 1,}, {1, 0}};
        int[][] res = matrixPower(basic, N - 2);
        return 2 * res[0][0] + res[1][0];
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
            int ans1 = getNum1(N);
            int ans2 = getNum2(N);

            if (ans1 != ans2) {
                success = false;
                System.out.println("N = " + N);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
            }
        }
        System.out.println(success ? "Nice" : "F");
    }

    public static void main(String[] args) {
        test();
    }

}

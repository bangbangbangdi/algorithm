package my_practise.p2023.basic.algorithm.dynamicProgramming.scope;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.dynamicProgramming.scope
 * className:      MinPathSum
 * author:     BangDi
 * description: 最小路径和
 * //给定一个矩阵 m，从左上角开始每次只能向右或者向下走，
 * //最后到达右下角的位置，路径上所有的数字累加起来就是路径和
 * //返回所有的路径中最小的路径和
 * date:    2023/5/30 13:49
 * version:    1.0
 */
public class MinPathSum {

    public static int minPathSum(int[][] m) {
        if (m == null || m.length < 1 || m[0].length < 1) {
            return Integer.MAX_VALUE;
        }
        return process(m, 0, 0);
    }

    public static int process(int[][] m, int x, int y) {
        if (x == m.length - 1 && y == m[0].length - 1) {
            return m[x][y];
        }
        if (x == m.length - 1) {
            return m[x][y] + process(m, x, y + 1);
        }
        if (y == m[0].length - 1) {
            return m[x][y] + process(m, x + 1, y);
        }
        return m[x][y] + Math.min(process(m, x + 1, y), process(m, x, y + 1));
    }

    public static int dpWays(int[][] m) {
        if (m == null || m.length < 1 || m[0].length < 1) {
            return Integer.MAX_VALUE;
        }
        int W = m.length - 1;
        int H = m[0].length - 1;
        int[][] dp = new int[W + 1][H + 1];
        dp[W][H] = m[W][H];
        for (int w = W - 1; w >= 0; w--) {
            dp[w][H] = dp[w + 1][H] + m[w][H];
        }
        for (int i = H - 1; i >= 0; i--) {
            dp[W][i] = dp[W][i + 1] + m[W][i];
        }
        for (int curX = W - 1; curX >= 0; curX--) {
            for (int curY = H - 1; curY >= 0; curY--) {
                dp[curX][curY] = m[curX][curY] + Math.min(dp[curX + 1][curY], dp[curX][curY + 1]);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1},
                {8, 8, 4, 0}};
        System.out.println(minPathSum(m));
        System.out.println(dpWays(m));
        //System.out.println(min2(m));
        //System.out.println(dpWayPro(m));

    }
}
package basicAlgorithm.dynamicProgramming.scope;

//给定一个矩阵 m，从左上角开始每次只能向右或者向下走，
//最后到达右下角的位置，路径上所有的数字累加起来就是路径和
//返回所有的路径中最小的路径和
public class MinPathSum {

    public static int min(int[][] m) {
        if (m == null || m.length < 1 || m[0] == null || m[0].length < 1) {
            return 0;
        }
        return process(m, m.length - 1, m[0].length - 1, 0, 0);
    }

    public static int process(int[][] m, int tarX, int tarY, int curX, int curY) {
        if (curX == m.length || curY == m[0].length) {
            return Integer.MAX_VALUE;
        }
        if (curX == tarX && curY == tarY) {
            return m[curX][curY];
        }
        return Math.min(process(m, tarX, tarY, curX + 1, curY), process(m, tarX, tarY, curX, curY + 1)) + m[curX][curY];
    }

    public static int dpWays(int[][] m) {
        if (m == null || m.length < 1 || m[0] == null || m[0].length < 1) {
            return 0;
        }
        int[][] dp = new int[m.length][m[0].length];
        int tarX = m.length - 1;
        int tarY = m[0].length - 1;
        dp[tarX][tarY] = m[tarX][tarY];
        for (int x = tarX - 1; x >= 0; x--) {
            dp[x][tarY] = dp[x + 1][tarY] + m[x][tarY];
        }
        for (int y = tarY - 1; y >= 0; y--) {
            dp[tarX][y] = dp[tarX][y + 1] + m[tarX][y];
        }
        for (int x = tarX - 1; x >= 0; x--) {
            for (int y = tarY - 1; y >= 0; y--) {
                dp[x][y] = Math.min(dp[x + 1][y], dp[x][y + 1]) + m[x][y];
            }
        }
        return dp[0][0];
    }

    public static int min2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        return process2(m, m.length - 1, m[0].length - 1);
    }

    public static int process2(int[][] m, int curX, int curY) {
        if (curX == 0 && curY == 0) {
            return m[0][0];
        }
        if (curX == 0) {
            return process2(m, curX, curY - 1) + m[curX][curY];
        }
        if (curY == 0) {
            return process2(m, curX - 1, curY) + m[curX][curY];
        }
        return Math.min(process2(m, curX - 1, curY), process2(m, curX, curY - 1)) + m[curX][curY];
    }

    public static int dpWayPro(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int length = m.length;
        int width = m[0].length;
        int[] buffer = new int[width];
        for (int i = width - 1; i >= 0; i--) {
            buffer[i] = i == width - 1 ? m[length - 1][i] : m[length - 1][i] + m[length - 1][i];
        }
        for (int x = length - 2; x >= 0; x--) {
            for (int y = width - 1; y >= 0; y--) {
                buffer[y] = y == width - 1 ? buffer[y] + m[x][y] : Math.min(buffer[y + 1], buffer[y]) + m[x][y];
            }
        }
        return buffer[0];
    }

    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1},
                {8, 8, 4, 0}};
        System.out.println(min(m));
        System.out.println(dpWays(m));
        System.out.println(min2(m));
        System.out.println(dpWayPro(m));

    }
}
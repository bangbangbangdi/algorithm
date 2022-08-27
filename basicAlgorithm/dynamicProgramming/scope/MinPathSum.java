package basicAlgorithm.dynamicProgramming.scope;

//给定一个矩阵 m，从左上角开始每次只能向右或者向下走，
//最后到达右下角的位置，路径上所有的数字累加起来就是路径和
//返回所有的路径中最小的路径和
public class MinPathSum {

    public static int min(int[][] m) {
        if (m == null || m.length < 1 || m[0] == null || m[0].length < 1) {
            return 0;
        }
        return process(m, m.length - 1, m[0].length - 1, 0, 0, 0);
    }

    public static int process(int[][] m, int tarX, int tarY, int curX, int curY, int count) {
        if (curX == m.length || curY == m[0].length) {
            return Integer.MAX_VALUE;
        }
        if (curX == tarX && curY == tarY) {
            return count + m[curX][curY];
        }
        int p1 = process(m, tarX, tarY, curX + 1, curY, count + m[curX][curY]);
        int p2 = process(m, tarX, tarY, curX, curY + 1, count + m[curX][curY]);
        return Math.min(p1, p2);
    }

    public static void main(String[] args) {
        int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 },
                { 8, 8, 4, 0 } };
        System.out.println(min(m));
    }

}
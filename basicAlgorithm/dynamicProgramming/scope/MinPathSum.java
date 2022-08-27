package basicAlgorithm.dynamicProgramming.scope;

// 给定一个矩阵 m，从左上角开始每次只能向右或者向下走，
//最后到达右下角的位置，路径上所有的数字累加起来就是路径和
//返回所有的路径中最小的路径和
public class MinPathSum {

    public static int min(int[][] m) {
        if (m == null || m.length < 1) {
            return 0;
        }
        int N = m.length;
        int M = m[0].length;

        return 1;
    }

    public static int process(int[][] m, int tarX, int tarY, int curX, int curY,int count) {
        if (curX > m.length - 1 || curY > m[0].length - 1) {
            return Integer.MAX_VALUE;
        }
        if (curX == tarX && curY == tarY){
            return count;
        }
        return 1;
    }

}
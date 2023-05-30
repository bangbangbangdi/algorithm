package my_practise.p2023.basic.algorithm.dynamicProgramming.scope;

import basicAlgorithm.dynamicProgramming.leftToRight.Knapsack;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.dynamicProgramming.scope
 * className:      HorseJump
 * author:     BangDi
 * description:  马走日
 * //请同学们自行搜索或者想象一个象棋的棋盘，
 * //        然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * //        那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域 (0<=x<=8 0<=y<=9)
 * //        给你三个 参数 x，y，k
 * //        返回“马”从(0,0)位置出发，必须走k步
 * //        最后落在(x,y)上的方法数有多少种?
 * date:    2023/5/30 11:07
 * version:    1.0
 */
public class HorseJump {

    public static int horseJump(int x, int y, int k) {
        if (x < 0 || x > 8 || y < 0 || y > 9 || k <= 0) {
            return -1;
        }
        int[][] param = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        return process(x, y, k, 0, 0, param);
    }

    public static int process(int finX, int finY, int k, int curX, int curY, int[][] param) {
        if (curX < 0 || curY < 0 || curX > 8 || curY > 9) {
            return 0;
        }
        if (k == 0) {
            return ((curX == finX) && (curY == finY)) ? 1 : 0;
        }
        int res = 0;
        for (int[] ints : param) {
            res += process(finX, finY, k - 1, curX + ints[0], curY + ints[1], param);
        }
        return res;
    }

    public static int dpWays(int x,int y,int k){
        if (x < 0 || x > 8 || y < 0 || y > 9 || k <= 0) {
            return -1;
        }
        int[][][] dp = new int[9][10][k+1];
        int[][] param = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        dp[x][y][0] = 1;
        for (int curK = 1; curK <= k; curK++) {
            for (int curX = 0; curX < 9; curX++) {
                for (int curY = 0; curY < 10; curY++) {
                    for (int[] ints : param) {
                        if (curX + ints[0] > 8 || curX + ints[0] < 0 || curY + ints[1] > 9 || curY + ints[1] < 0){
                            continue;
                        }
                        dp[curX][curY][curK] += dp[curX + ints[0]][curY + ints[1]][curK-1];
                    }
                }
            }
        }
        return dp[0][0][k];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int k = 10;
        //int x = 2;
        //int y = 1;
        //int k = 3;
        System.out.println(horseJump(x, y, k));
        System.out.println(dpWays(x, y, k));
    }
}

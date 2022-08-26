package basicAlgorithm.dynamicProgramming.scope;
//请同学们自行搜索或者想象一个象棋的棋盘，
//        然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
//        那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域 (0<=x<=8 0<=y<=9)
//        给你三个 参数 x，y，k
//        返回“马”从(0,0)位置出发，必须走k步
//        最后落在(x,y)上的方法数有多少种?

public class HorseJump {

    public static int horseJump(int x, int y, int k) {
        if (x < 0 || x > 8 || y < 0 || y > 9 || k < 0) {
            return 0;
        }
        int[][] available = new int[8][2];
        available[0] = new int[]{2, 1};
        available[1] = new int[]{2, -1};
        available[2] = new int[]{-2, 1};
        available[3] = new int[]{-2, -1};
        available[4] = new int[]{1, 2};
        available[5] = new int[]{1, -2};
        available[6] = new int[]{-1, 2};
        available[7] = new int[]{-1, -2};
        return process(x, y, available, 0, 0, k);
    }

    public static int process(int x, int y, int[][] available, int curX, int curY, int k) {
        if (k == 0) {
            return (curX == x && curY == y) ? 1 : 0;
        }
        if (curX < 0 || curX > 8 || curY < 0 || curY > 9) {
            return 0;
        }
        int res = 0;
        for (int[] ints : available) {
            res += process(x, y, available, curX + ints[0], curY + ints[1], k - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int k = 10;
        System.out.println(horseJump(x, y, k));
    }
}

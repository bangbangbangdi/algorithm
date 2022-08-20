package basicAlgorithm.dynamicProgramming.scope;

import tool.Tools;

// 给定一个整形数组arr,代表数值不同的纸牌排成一条线
// 玩家A和玩家B依次拿走每张牌
// 规定玩家A先拿,玩家B后拿
// 但是每个玩家每次只能拿走最走或最右的纸牌
// 玩家A和玩家B都决定聪明,请返回最后获胜者的分数
public class CardsInLine {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(first(arr, 0, arr.length - 1), defensive(arr, 0, arr.length - 1));
    }


    public static int first(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        return Math.max(arr[L] + defensive(arr, L + 1, R), arr[R] + defensive(arr, L, R - 1));
    }

    public static int defensive(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        return Math.min(first(arr, L, R - 1), first(arr, L + 1, R));
    }

    public static int win2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        return dpWays(arr);
    }

    public static int dpWays(int[] arr) {
        int N = arr.length;
        int[][] firstDp = new int[N][N];
        int[][] defensiveDp = new int[N][N];
        // i --> R
        for (int i = 0; i < N; i++) {
            // j --> L
            for (int j = 0; j < N - i; j++) {
                if (i == 0) {
                    firstDp[j][j] = arr[j];
                } else {
                    firstDp[i + j][j] = Math.max(arr[j] + defensiveDp[i + j][j + 1], arr[i + j] + defensiveDp[i + j - 1][j]);
                    defensiveDp[i + j][j] = Math.min(firstDp[j][i + j - 1], firstDp[j + 1][i + j]);
                }
            }
        }

        return Math.max(firstDp[N - 1][0], defensiveDp[N-1][0]);
    }

    public static void main(String[] args) {
        int[] arr = {16, 65, -39, -81, -39, -62, 4, 4, 54, -65, -6, -2, -54, 26, -8, 21, 15, -10, -13, 1, -68, -57, 9, -59, 2, 26, 41};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
//        int[] arr = Tools.generateRandomArray(100, 100);
//        Tools.printArray(arr);
    }

}

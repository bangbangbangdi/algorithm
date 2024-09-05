package my_practise.p2024.pro.algorithm;

import tool.Executable;
import tool.Tools;

/**
 * projectName:    algorithm
 * package:        my_practise.p2024.pro.algorithm
 * className:      SubMatrixMaxSum
 * author:     BangDi
 * description:
 * 给定一个矩阵,求最大子矩阵和
 * date:    2024/6/14 17:45
 * version:    1.0
 */
public class SubMatrixMaxSum {

    public static int matrixMaxSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return Integer.MIN_VALUE;
        }
        int N = Math.max(matrix.length, matrix[0].length);
        int M = N == matrix.length ? matrix[0].length : matrix.length;
        int[] help = new int[N];
        int pre = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            help = new int[N];
            for (int j = i; j < M; j++) {
                pre = 0;
                for (int k = 0; k < N; k++) {
                    pre = Math.max(0, pre);
                    help[k] += (N == matrix.length ? matrix[k][j] : matrix[j][k]);
                    pre += help[k];
                    max = Math.max(max, pre);
                }
            }
        }
        return max;
    }

    public static int compare(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return Integer.MIN_VALUE;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int res = Integer.MIN_VALUE;
        for (int sx = 0; sx < N; sx++) {
            for (int sy = 0; sy < M; sy++) {
                for (int ex = sx; ex < N; ex++) {
                    for (int ey = sy; ey < M; ey++) {
                        int cur = process(matrix, sx, sy, ex, ey);
                        res = Math.max(res, cur);
                    }
                }
            }
        }
        return res;
    }

    public static int process(int[][] matrix, int sx, int sy, int ex, int ey) {
        int res = 0;
        for (int x = sx; x <= ex; x++) {
            for (int y = sy; y <= ey; y++) {
                res += matrix[x][y];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Tools.execute(new Executable() {
            @Override
            public boolean execute() {
                int maxLength = 20;
                int maxWidth = 20;
                int[][] matrix = Tools.generateMatrix(maxLength, maxWidth);
                int res = matrixMaxSum(matrix);
                int compare = compare(matrix);
                if (res != compare) {
                    Tools.printMatrix(matrix);
                    System.out.println("res = " + res);
                    System.out.println("compare = " + compare);
                    return false;
                }
                return true;
            }
        });
    }
}

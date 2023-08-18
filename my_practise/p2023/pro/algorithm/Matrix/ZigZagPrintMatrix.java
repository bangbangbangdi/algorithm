package my_practise.p2023.pro.algorithm.Matrix;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm.Matrix
 * className:      ZigZagPrintMatrix
 * author:     BangDi
 * description:  锯齿状打印矩阵
 * date:    2023/8/18 21:05
 * version:    1.0
 */
public class ZigZagPrintMatrix {

    public static void zigzagPrint(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int W = matrix.length;
        int H = matrix[0].length;
        int N = W + H - 1;
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        boolean toDown = false;
        for (int i = 0; i < N; i++) {
            printDiagonal(matrix, x1, y1, x2, y2, toDown);
            y1 = x1 == W - 1 ? y1 + 1 : y1;
            x1 = x1 == W - 1 ? x1 : x1 + 1;
            x2 = y2 == H - 1 ? x2 + 1 : x2;
            y2 = y2 == H - 1 ? y2 : y2 + 1;
            toDown = !toDown;
        }
    }

    public static void printDiagonal(int[][] matrix, int x1, int y1, int x2, int y2, boolean toDown) {
        int xStart = toDown ? x1 : x2;
        int yStart = toDown ? y1 : y2;
        int xEnd = toDown ? x2 : x1;
        int yEnd = toDown ? y2 : y1;
        int xIncr = toDown ? -1 : 1;
        int yIncr = toDown ? 1 : -1;
        while (xStart != xEnd) {
            System.out.print(matrix[xStart][yStart] + "-");
            xStart += xIncr;
            yStart += yIncr;
        }
        System.out.print(matrix[xStart][yStart] + "-");
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 12, 11, 10}, {2, 13, 16, 9}, {3, 14, 15, 8}, {4, 5, 6, 7}};
        zigzagPrint(matrix);
    }

}

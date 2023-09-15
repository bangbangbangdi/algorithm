package my_practise.p2023.pro.algorithm.Matrix;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm.Matrix
 * className:      PrintMatrixSpiralOrder
 * author:     BangDi
 * description:  旋转打印矩阵
 * date:    2023/8/18 20:42
 * version:    1.0
 */
public class PrintMatrixSpiralOrder {

    public static void printSpiral(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int level = Math.min(matrix.length, matrix[0].length) % 2 == 0 ? Math.min(matrix.length, matrix[0].length) : Math.min(matrix.length, matrix[0].length) + 1;
        for (int i = 0; i < level; i++) {
            printEdge(matrix, i);
        }
    }

    public static void printEdge(int[][] matrix, int level) {
        int xStart = level;
        int yStart = level;
        int xEnd = matrix.length - level - 1;
        int yEnd = matrix[0].length - level - 1;
        if (xStart == xEnd) {
            while (yStart <= yEnd) {
                System.out.print(matrix[xStart][yStart++] + "-");
            }
        }
        if (yStart == yEnd) {
            while (xStart <= xEnd) {
                System.out.print(matrix[xStart++][yStart] + "-");
            }
        }
        while (xStart < xEnd) {
            System.out.print(matrix[xStart++][yStart] + "-");
        }
        while (yStart < yEnd) {
            System.out.print(matrix[xStart][yStart++] + "-");
        }
        xEnd = level;
        yEnd = level;
        while (xStart > xEnd) {
            System.out.print(matrix[xStart--][yStart] + "-");
        }
        while (yStart > yEnd) {
            System.out.print(matrix[xStart][yStart--] + "-");
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 12, 11, 10}, {2, 13, 16, 9}, {3, 14, 15, 8}, {4, 5, 6, 7}};
        printSpiral(matrix);
    }

}

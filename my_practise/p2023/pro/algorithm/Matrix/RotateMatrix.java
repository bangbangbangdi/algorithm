package my_practise.p2023.pro.algorithm.Matrix;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm.Matrix
 * className:      RotateMatrix
 * author:     BangDi
 * description:  将正方矩形旋转90度,例如:
 * date:    2023/8/17 22:54
 * version:    1.0
 */
public class RotateMatrix {

    public static void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length / 2; i++) {
            rotateEdge(matrix, i);
        }
    }

    public static void rotateEdge(int[][] matrix, int level) {
        int N = matrix.length - 1 - level;
        int tmp = 0;
        for (int i = 0; i < N; i++) {
            tmp = matrix[level][N - i];
            matrix[level][N - i] = matrix[N - i][N];
            matrix[N - i][N] = matrix[N][i];
            matrix[N][i] = matrix[level + i][level];
            matrix[level + i][level] = tmp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix.length; x++) {
                System.out.print(getSpace(matrix[x][y]));
            }
            System.out.println();
        }
    }

    public static String getSpace(int num) {
        return num < 10 ? " " + num + "  " : " " + num + " ";
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,12,11,10},{2,13,16,9},{3,14,15,8},{4,5,6,7}};
        printMatrix(matrix);
        System.out.println("==============");
        rotate(matrix);
        printMatrix(matrix);
    }

}

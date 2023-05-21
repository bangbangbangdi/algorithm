package my_practise.p2023.basic.algorithm.bitoperation;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.bitoperation
 * className:      PrintBinaryCode
 * author:     BangDi
 * description:  打印一个数的二进制形式
 * date:    2023/5/19 9:23
 * version:    1.0
 */
public class PrintBinaryCode {

    public static void printBinaryCode(int num) {
        int i = 1;
        for (int j = 31; j >= 0; j--) {
            System.out.print((num & (i << j)) == 0 ? 0 : 1);
        }
    }

    public static void main(String[] args) {
        printBinaryCode(Integer.MAX_VALUE);
        System.out.println();
        printBinaryCode(Integer.MIN_VALUE);
    }

}

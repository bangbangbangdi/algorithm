package my_practise.p2023.basic.algorithm.bitoperation;

import tool.Tools;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.algorithm.bitoperation
 * className:      NQueensPro
 * author:     BangDi
 * description:  N皇后问题
 * date:    2023/5/21 20:20
 * version:    1.0
 */
public class NQueensPro {

    public static int getQueens(int N) {
        if (N == 0) {
            return -1;
        }
        int[] record = new int[N];
        return process(record, 0);
    }

    public static int process(int[] record, int index) {
        if (record.length == index) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < record.length; i++) {
            record[index] = i;
            if (available(record, index)) {
                res += process(record, index + 1);
            }
        }
        return res;
    }

    public static boolean available(int[] record, int index) {
        for (int i = 0; i < index; i++) {
            if (record[i] == record[index] || Math.abs(record[i] - record[index]) == Math.abs(i - index)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int N = 10;
        System.out.println(getQueens(N));
    }

}

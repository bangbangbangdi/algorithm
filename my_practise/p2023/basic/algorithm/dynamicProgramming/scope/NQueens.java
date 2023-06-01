package my_practise.p2023.basic.algorithm.dynamicProgramming.scope;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.dynamicProgramming.scope
 * className:      NQueens
 * author:     BangDi
 * description:  N皇后问题
 * date:    2023/5/31 11:31
 * version:    1.0
 */
public class NQueens {

    public static int queens(int N) {
        if (N < 1) {
            return 0;
        }
        return process(new int[N], 0);
    }

    public static int process(int[] arr, int K) {
        if (K == arr.length) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[K] = i;
            if (isAvailable(arr, K)) {
                res += process(arr, K + 1);
            }
            arr[K] = 0;
        }
        return res;
    }

    public static boolean isAvailable(int[] arr, int k) {
        for (int i = 0; i < k; i++) {
            if (arr[i] == arr[k] || Math.abs(arr[i] - arr[k]) == k - i) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int N = 5;
        System.out.println(queens(N));
    }
}

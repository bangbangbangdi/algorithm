package my_practise.p2023.pro.algorithm.Arr;

import tool.Tools;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm.Arr
 * className:      LongestSumSubArrayLengthInPositiveArray
 * author:     BangDi
 * description:
 * 给定一个正整数组成的无序数据arr,给定一个正整数值K
 * 找到arr的所有自数组里,哪个子数组的累加和等于K,并且长度是最大的
 * 返回其长度
 * date:    2023/8/18 21:37
 * version:    1.0
 */
public class LongestSumSubArrayLengthInPositiveArray {

    public static int compare(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int L = 0;
        int maxLen = 0;
        while (L < arr.length) {
            int R = L;
            int sum = 0;
            while (R < arr.length) {
                sum += arr[R++];
                if (sum == k) {
                    maxLen = Math.max(maxLen, R - L);
                }
            }
            L++;
        }
        return maxLen;
    }

    public static int getMaxLen(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int L = 0;
        int R = 0;
        int maxLen = 0;
        int sum = 0;

        while (L < arr.length) {
            while (R < arr.length && sum <= k) {
                if (sum == k) {
                    maxLen = Math.max(maxLen, R - L);
                }
                sum += arr[R++];
            }
            if (sum == k) {
                maxLen = Math.max(maxLen, R - L);
                if (R == arr.length - 1) {
                    return maxLen;
                }
            }
            sum -= arr[L++];
        }

        return maxLen;
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue, 1);
            int k = (int) (100 * Math.random());
            int res1 = getMaxLen(arr, k);
            int res2 = compare(arr, k);
            if (res1 != res2) {
                succeed = false;
                Tools.printArray(arr);
                System.out.println("k = " + k);
                System.out.println("res1 = " + res1);
                System.out.println("compare = " + res2);
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
    }

}

package my_practise.p2023.pro.algorithm.Arr;

import tool.Tools;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm.Arr
 * className:      LongestLessSumSubArrayLength
 * author:     BangDi
 * description:
 * 给定一个整数组成的无序数据arr,值可能正,负,0
 * 给定一个整数值K
 * 找到arr的所有子数组里,哪个子数组的累加和等于K,并且长度是最大的
 * 返回其长度
 * date:    2023/8/19 08:28
 * version:    1.0
 */
public class LongestLessSumSubArrayLength {

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
                if (sum <= k) {
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
        int N = arr.length;
        int[] minEnd = new int[N];
        int[] minSum = new int[N];
        int L = 0;
        int maxLen = 0;
        minEnd[N - 1] = N - 1;
        minSum[N - 1] = arr[N - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            minSum[i] = minSum[i + 1] <= 0 ? arr[i] + minSum[i + 1] : arr[i];
            minEnd[i] = minSum[i + 1] <= 0 ? minEnd[i + 1] : i;
        }
        int R = minEnd[0] + 1;
        int sum = minSum[0];
        while (L < arr.length) {
            while (R < arr.length && sum <= k) {
                maxLen = Math.max(maxLen, R - L);
                sum += minSum[R];
                R = minEnd[R] + 1;
            }
            if (sum <= k) {
                maxLen = Math.max(maxLen, R - L);
            }
            if (maxLen == (R - L) && R == arr.length) {
                return maxLen;
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
            int[] arr = Tools.generateRandomArray(maxSize, maxValue);
            int k = (int) (maxSize * maxValue / 2 * Math.random() - maxSize * maxValue / 2 * Math.random());
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
//        int[] arr = {1, -3, 0, 3, 6};
//        int k = 3;
//        compare(arr, k);
//        getMaxLen(arr, k);
        test();
    }

}

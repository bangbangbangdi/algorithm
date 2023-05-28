package my_practise.p2023.basic.algorithm.recursion;

import tool.Tools;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.recursion
 * className:      SmallSum
 * author:     BangDi
 * description:  求数组小和
 * date:    2023/5/26 16:04
 * version:    1.0
 */
//在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
//例子: [1,3,4,2,5]
//1左边比1小的数：没有
//3左边比3小的数：1
//4左边比4小的数：1、3
//2左边比2小的数：1
//5左边比5小的数：1、3、4、 2
//所以数组的小和为1+1+3+1+1+3+4+2=16
public class SmallSum {

    // 思路: 归并排序!!在归并的过程中算出最小和
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L >= R){
            return 0;
        }
        int res = 0;
        // 注意此处选择上中点还是下中点的问题,此处必须选择上中点
        int mid = L + ((R - L) >> 1);
        res += process(arr, L, mid);
        res += process(arr, mid + 1, R);
        res += merge(arr, L, R, mid);
        return res;
    }

    public static int merge(int[] arr, int L, int R, int mid) {
        int[] help = new int[R - L + 1];
        int index = 0;
        int p = L;
        int p1 = mid + 1;
        int res = 0;
        while (p <= mid && p1 <= R) {
            res += arr[p] < arr[p1] ? arr[p] * (R - p1 + 1) : 0;
            help[index++] = arr[p] < arr[p1] ? arr[p++] : arr[p1++];
        }
        while (p <= mid) {
            help[index++] = arr[p++];
        }
        while (p1 <= R) {
            help[index++] = arr[p1++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }


    public static int compare(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                res += arr[i] > arr[j] ? arr[j] : 0;
            }
        }
        return res;
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue, false);
            int[] copyArr = Tools.copyArr(arr);
            int sum = smallSum(arr);
            int compare = compare(copyArr);
            if (sum != compare) {
                succeed = false;
                Tools.printArray(copyArr);
                System.out.println("sum : " + sum);
                System.out.println("compare : " + compare);
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
    }

}

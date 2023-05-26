package my_practise.p2023.basic.algorithm.sortAlgorithm;

import tool.Tools;

import java.util.Arrays;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.algorithm.sortAlgorithm
 * className:      RadixSort
 * author:     BangDi
 * description:  基数排序:不基于比较排序的一种,其核心思想就是按照进制由进制底的位往进制高的位排序
 * date:    2023/5/24 23:34
 * version:    1.0
 */
public class RadixSort {

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = arr[0];
        for (int i : arr) {
            max = Math.max(max, i);
        }
        radixSort(arr, 0, arr.length - 1, maxBit(max));
    }

    public static void radixSort(int[] arr, int L, int R, int maxBit) {
        final int RADIX = 10;
        int[] count = new int[RADIX];
        int[] help = new int[R - L + 1];
        int b = 0;
        while (b < maxBit) {
            for (int i = L; i <= R; i++) {
                count[getDigit(arr[i], b)]++;
            }
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }
            for (int i = R; i >= L; i--) {
                int index = --count[getDigit(arr[i],b)];
                help[index] = arr[i];
            }
            for (int i = L; i <= R; i++) {
                arr[i] = help[i];
            }
            Arrays.fill(count,0);
            b++;
        }
    }

    // 获得最大进位数
    public static int maxBit(int value) {
        int res = 0;
        while (value != 0) {
            value /= 10;
            res++;
        }
        return res;
    }

    // 获得对应进位的值
    public static int getDigit(int value, int digit) {
        return (value / (int) Math.pow(10, digit)) % 10;
    }


    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue, false);
            int[] copy = Tools.copyArr(arr);
//            Tools.printArray(copy);
            radixSort(arr);
            Arrays.sort(copy);
            if (!Tools.isEqual(arr, copy)) {
                Tools.printArray(arr);
                Tools.printArray(copy);
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
//        int[] arr = Tools.generateRandomArray(10, 10,false);
//        Tools.printArray(arr);
//        radixSort(arr);
//        Tools.printArray(arr);
        test();
    }

}

package basicAlgorithm.sortAlgorithm;

import tool.Tools;

import java.util.Arrays;

public class RadixSort {

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int digit = maxBits(arr);
        radixSort(arr, 0, arr.length - 1, digit);
    }

    public static int maxBits(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int ans = 0;
        while (max != 0) {
            ans++;
            max /= 10;
        }
        return ans;
    }

    // 十进制下的radixSort 基数排序
    public static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;
        int[] help = new int[R - L + 1];
        int[] count = new int[radix];
        int d = 0;
        while (d < digit) {
            for (int i : arr) {
                count[getDigit(i, d)]++;
            }
            for (int i = 1; i < count.length; i++) {
                count[i] = count[i - 1] + count[i];
            }
            for (int i = R; i >= L; i--) {
                int index = getDigit(arr[i], d);
                help[--count[index]] = arr[i];
            }
            for (int i = 0; i < help.length; i++) {
                arr[L + i] = help[i];
            }
            Arrays.fill(count, 0);
            d++;
        }

    }

    public static int getDigit(int value, int digit) {
        return (value / (int) (Math.pow(10, digit))) % 10;
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 200;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue, false);
            int[] copyArr = Tools.copyArr(arr);
            radixSort(arr);
            Arrays.sort(copyArr);
            if (!Tools.isEqual(arr, copyArr)) {
                succeed = false;
                System.out.println("=======");
                Tools.printArray(arr);
                Tools.printArray(copyArr);
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
//        int i = 1;
//        int[] arr = {1,2,3,4,5};
//        System.out.println(arr[--i]);
//        System.out.println(i);
    }
}
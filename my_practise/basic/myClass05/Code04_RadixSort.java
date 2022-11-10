package my_practise.basic.myClass05;

import tool.Tools;

public class Code04_RadixSort {
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxBits(arr));
    }

    public static int maxBits(int[] arr) {
        int max = Integer.MIN_VALUE;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        while (max > 0) {
            max /= 10;
            res++;
        }
        return res;
    }

    public static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
//        int[] count = new int[radix];
        int[] help = new int[R - L + 1];
        for (int d = 0; d < digit; d++) {
            int[] count = new int[radix];
            for (i = L; i <= R; i++) {
                count[getDigit(arr[i], d)]++;
            }
            for (i = 1; i < count.length; i++) {
                count[i] = count[i - 1] + count[i];
            }
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                help[count[j] - 1] = arr[i];
                count[j]--;
            }
            for (i = 0; i < help.length; i++) {
                arr[L + i] = help[i];
            }
        }
    }

    public static int getDigit(int x, int d) {
        return ((int) (x / Math.pow(10, d))) % 10;
    }

    public static void main(String[] args) {
        int testTimes = 500000;
        int maxSize = 100;
        int maxValue = 1000;
        boolean succeed = true;
        for (int i = 0; (i < testTimes) && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue, false);
            int[] copyArr = Tools.copyArr(arr);
//            Tools.printArray(arr);
            radixSort(arr);
            Tools.comparator(copyArr);
            if (!Tools.isEqual(arr, copyArr)) {
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }
}
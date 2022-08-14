package basicAlgorithm.sortAlgorithm;

import tool.Tools;

import java.util.Arrays;

public class CountSort {

    // 样本数据在 0 ~ 200 之间
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0) {
                arr[index++] = i;
            }
        }
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 200;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue, false);
            int[] copyArr = Tools.copyArr(arr);
//            Tools.printArray(arr);
            countSort(arr);
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
    }

}

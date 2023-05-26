package my_practise.p2023.basic.algorithm.sortAlgorithm;

import tool.Tools;

import java.util.Arrays;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.algorithm.sortAlgorithm
 * className:      SelectionSort
 * author:     BangDi
 * description:  选择排序
 * date:    2023/5/25 23:48
 * version:    1.0
 */
public class SelectionSort {
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            Tools.swap(arr, minIndex, i);
        }
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
            selectionSort(arr);
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
        test();
    }
}

package my_practise.p2023.basic.algorithm.sortAlgorithm;

import tool.Tools;

import java.util.Arrays;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.sortAlgorithm
 * className:      InsertionSort
 * author:     BangDi
 * description:  插入排序
 * date:    2023/5/23 14:59
 * version:    1.0
 */
public class InsertionSort {

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                Tools.swap(arr,j,j-1);
            }
        }
    }


    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue);
            int[] copy = Tools.copyArr(arr);
            insertionSort(arr);
            //Tools.printArray(copy);
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

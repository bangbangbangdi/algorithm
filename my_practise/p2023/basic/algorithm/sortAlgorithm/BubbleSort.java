package my_practise.p2023.basic.algorithm.sortAlgorithm;

import tool.Tools;

import java.util.Arrays;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.sortAlgorithm
 * className:      BubbleSort
 * author:     BangDi
 * description:  冒泡排序
 * date:    2023/5/22 17:46
 * version:    1.0
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    Tools.swap(arr, j, j - 1);
                }
            }
        }
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue, true);
            int[] copy = Tools.copyArr(arr);
            bubbleSort(arr);
            Arrays.sort(copy);
            if (!Tools.isEqual(arr,copy)){
                succeed = false;
            }
        }

        System.out.println(succeed ? "Nice" : "Fuck");
    }
    public static void main(String[] args) {
        test();
    }

}

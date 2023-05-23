package my_practise.p2023.basic.algorithm.sortAlgorithm;

import tool.Tools;

import java.util.Arrays;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.sortAlgorithm
 * className:      HeapSort
 * author:     BangDi
 * description:  堆排序
 * date:    2023/5/23 9:15
 * version:    1.0
 */
public class HeapSort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        while(heapSize != 0){
            Tools.swap(arr,0,--heapSize);
            heapify(arr,0,heapSize);
        }
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            Tools.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int leftIndex = index * 2 + 1;
        while (leftIndex < heapSize) {
            int largestIndex = (leftIndex + 1 < heapSize) && arr[leftIndex] < arr[leftIndex + 1] ? leftIndex + 1 : leftIndex;
            largestIndex = arr[largestIndex] < arr[index] ? index : largestIndex;
            if (largestIndex == index) {
                break;
            }
            Tools.swap(arr, index, largestIndex);
            index = largestIndex;
            leftIndex = index * 2 + 1;
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
            heapSort(arr);
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
        //int[] arr = new int[]{3,10,0,4,-6,-1};
        // Tools.printArray(arr);
        // heapSort(arr);
        // Tools.printArray(arr);


        test();
    }

}

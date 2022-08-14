package basicAlgorithm.sortAlgorithm;

import tool.Tools;

import java.util.Arrays;

public class HeapSort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        while (heapSize > 0) {
            Tools.swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }

    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int leftIndex = index * 2 + 1;
        while (leftIndex < heapSize) {
            int largestIndex = leftIndex + 1 < heapSize && arr[leftIndex] < arr[leftIndex + 1] ? leftIndex + 1 : leftIndex;
            largestIndex = arr[index] < arr[largestIndex] ? largestIndex : index;
            if (index == largestIndex) {
                break;
            }
            Tools.swap(arr, index, largestIndex);
            index = largestIndex;
            leftIndex = index * 2 + 1;
        }
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] < arr[(index - 1) / 2]) {
            Tools.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue);
            int[] copyArr = Tools.copyArr(arr);
//            Tools.printArray(arr);
            heapSort(arr);
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
//        int[] arr = {7,9,-8,28,-12};
//        heapSort(arr);
        test();
    }
}

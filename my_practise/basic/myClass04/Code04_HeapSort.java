package my_practise.basic.myClass04;

import tool.Tools;

public class Code04_HeapSort {
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int heapSize = arr.length;
        while (heapSize > 0) {
            Tools.swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            Tools.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = (left + 1 < heapSize) && (arr[left + 1] > arr[left]) ? left + 1 : left;
            largest = arr[index] < arr[largest] ? largest : index;
            if (largest == index) break;
            Tools.swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxValue = 100;
        int maxSize = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue);
            int[] copyArr = Tools.copyArr(arr);
            heapSort(arr);
            Tools.comparator(copyArr);
            if (!Tools.isEqual(arr, copyArr)) {
                succeed = false;
                System.out.println("heapSort:");
                Tools.printArray(arr);
                System.out.println("comparator");
                Tools.printArray(copyArr);
                break;
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }
}
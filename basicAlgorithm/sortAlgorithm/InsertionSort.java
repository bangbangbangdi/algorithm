package basicAlgorithm.sortAlgorithm;

import tool.Tools;

import java.util.Arrays;

public class InsertionSort {

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0 && arr[j] < arr[j - 1]; j--) {
                Tools.swap(arr,j,j-1);
            }
        }
    }

    public static void test() {
        int testTime = 1000000;
        int maxValue = 100;
        int maxSize = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue);
            int[] copyArr = Tools.copyArr(arr);
            insertionSort(arr);
            Arrays.sort(copyArr);
            succeed = Tools.isEqual(arr, copyArr);
            if (!succeed) {
                Tools.printArray(arr);
                System.out.println("-------------");
                Tools.printArray(copyArr);
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
    }

}

package basicAlgorithm.sortAlgorithm;

import tool.Tools;

import java.util.Arrays;

public class SelectionSort {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length == 0 ) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                min = arr[min] < arr[j] ? min:j;
            }
            Tools.swap(arr, i, min);
        }
    }

    public static void test(){
        int testTime = 1000000;
        int maxValue = 100;
        int maxSize = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue);
            int[] copyArr = Tools.copyArr(arr);
            selectionSort(arr);
            Arrays.sort(copyArr);
            succeed = Tools.isEqual(arr, copyArr);
            if (!succeed){
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

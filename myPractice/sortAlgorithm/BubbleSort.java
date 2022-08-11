package myPractice.sortAlgorithm;

import tool.Tools;

import java.util.Arrays;

public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if(arr[j] < arr[j-1]){
                    Tools.swap(arr,j,j-1);
                }
            }
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
            bubbleSort(arr);
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

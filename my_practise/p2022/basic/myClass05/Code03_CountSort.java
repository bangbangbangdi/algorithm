package my_practise.p2022.basic.myClass05;


import tool.Tools;

public class Code03_CountSort {
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
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

    public static void main(String[] args) {
        int testTimes = 500000;
        int maxValue = 100;
        int maxSize = 100;
        boolean succeed = true;
        for (int i = 0; (i < testTimes) && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue, false);
            int[] copyArr = Tools.copyArr(arr);
//            Tools.printArray(arr);
            countSort(arr);
            Tools.comparator(copyArr);
            if (!Tools.isEqual(arr, copyArr)) {
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }
}
package my_practise.p2023.basic.algorithm.sortAlgorithm;

import tool.Tools;

import java.util.Arrays;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.sortAlgorithm
 * className:      MergeSort
 * author:     BangDi
 * description:  归并排序
 * date:    2023/5/23 15:35
 * version:    1.0
 */
public class MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R){
            return ;
        }
        int M = L + ((R-L) >> 1);
        process(arr,L,M);
        process(arr,M+1,R);
        int p = L;
        int p1 = M+1;
        int[] help = new int[R - L + 1];
        int index = 0;
        while(p <= M && p1 <= R){
            help[index++] = arr[p] < arr[p1] ? arr[p++] : arr[p1++];
        }
        while(p <= M){
            help[index++] = arr[p++];
        }
        while(p1 <= R){
            help[index++] = arr[p1++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L+i] = help[i];
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
            mergeSort(arr);
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

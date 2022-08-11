package myPractice.sortAlgorithm;

import tool.Tools;

import java.util.Arrays;

public class MergeSort {

    public static void merge(int[] arr, int L, int R, int M) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        i = 0;
        while (i < help.length) {
            arr[L++] = help[i++];
        }
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        process(arr, L, M);
        process(arr, M + 1, R);
        merge(arr, L, R, M);
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int mergeSize = 1;
        while(mergeSize < N){
            int L = 0;
            while(L < N){
                int M = L + mergeSize - 1;
                if (M>=N){
                    break;
                }
                int R = Math.min(M+mergeSize,N-1);
                merge(arr,L,R,M);
                L = R + 1;
            }

            if (mergeSize>N/2){
                break;
            }
            mergeSize <<= 1;
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
            mergeSort2(arr);
            Arrays.sort(copyArr);
            succeed = Tools.isEqual(arr, copyArr);
            if (!succeed) {
                System.out.println("mergeSort");
                Tools.printArray(arr);
                System.out.println("comparator");
                Tools.printArray(copyArr);
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
//        int[] arr = {12, 3, 4, 1};
//        mergeSort2(arr);
//        Tools.printArray(arr);
        test();
    }
}

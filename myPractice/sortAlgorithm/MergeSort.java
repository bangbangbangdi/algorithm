package myPractice.sortAlgorithm;

import tool.Tools;

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
        while (L <= R) {
            arr[L++] = help[i++];
        }
    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R || arr.length == 1) {
            return;
        }
        int M = L + (R - L >> 1);
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


    public static void main(String[] args) {
        int[] arr = Tools.generateRandomArray(10, 10);
        mergeSort(arr);
        Tools.printArray(arr);
    }
}

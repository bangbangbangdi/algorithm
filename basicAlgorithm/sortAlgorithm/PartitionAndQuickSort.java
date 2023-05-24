package basicAlgorithm.sortAlgorithm;

import tool.Tools;

import java.util.Arrays;

public class PartitionAndQuickSort {

    public static int partition(int[] arr, int L, int R) {
        int lessEqual = L - 1;
        int i = L;
        while (i < R) {
            if (arr[i] <= arr[R]) {
                Tools.swap(arr, i, ++lessEqual);
            }
            i++;
        }
        Tools.swap(arr, i, ++lessEqual);
        return lessEqual;
    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int M = partition(arr, L, R);
        process(arr, L, M - 1);
        process(arr, M + 1, R);
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        int i = L;
        while (i < more) {
            if (arr[i] < arr[R]) {
                Tools.swap(arr, i++, ++less);
            } else if (arr[i] > arr[R]) {
                Tools.swap(arr, i, --more);
            } else {
                i++;
            }
        }
        Tools.swap(arr, more, R);
        return new int[]{++less, more};
    }

    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] ans = netherlandsFlag(arr, L, R);
        process2(arr, L, ans[0] - 1);
        process2(arr, ans[1] + 1, R);
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        Tools.swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] res = netherlandsFlag(arr, L, R);
        process3(arr, L, res[0] - 1);
        process3(arr, res[1] + 1, R);
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue);
            int[] comparator = Tools.copyArr(arr);
            int[] arr2 = Tools.copyArr(arr);
            int[] arr3 = Tools.copyArr(arr);
            quickSort(arr);
            quickSort2(arr2);
            quickSort3(arr3);
            Arrays.sort(comparator);
            succeed = Tools.isEqual(arr, comparator) && Tools.isEqual(arr2, comparator) && Tools.isEqual(arr3, comparator);
            if (!succeed) {
                Tools.printArray(arr);
                System.out.println("---------------");
                Tools.printArray(comparator);
                System.out.println("---------------");
                Tools.printArray(arr2);
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
    }
}

package my_practise.p2022.basic.myClass03;

import tool.Tools;

public class Code03_PartitionAndQuickSort {
    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = -1;
        int i = 0;
        while (i < R) {
            if (arr[i] <= arr[R]) {
                Tools.swap(arr, i, ++lessEqual);
            }
            i++;
        }
        Tools.swap(arr, ++lessEqual, R);
        return lessEqual;
    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int M = partition(arr, L, R);
        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }

    public static void quickSort1(int[] arr) {
        process1(arr, 0, arr.length - 1);
    }

    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] < arr[R]) {
                Tools.swap(arr, index++, ++less);
            } else if (arr[index] > arr[R]) {
                Tools.swap(arr, index, --more);
            } else {
                index++;
            }
        }
        Tools.swap(arr, index, R);
        return new int[]{less, more + 1};
    }

    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] equalArea = netherlandsFlag(arr, L, R);
        process2(arr, L, equalArea[0]);
        process2(arr, equalArea[1], R);
    }

    public static void quickSort2(int[] arr) {
        process2(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        Tools.swap(arr, (int) (L + (R - L + 1) * Math.random()), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArea[0]);
        process3(arr, equalArea[1], R);
    }

    public static void quickSort3(int[] arr) {
        process3(arr, 0, arr.length - 1);
    }


    public static void main(String[] args) {
        int textTime = 500000;
        int maxValue = 100;
        int maxSize = 100;
        boolean succeed = true;
        for (int i = 0; i < textTime; i++) {
            int[] arr1 = Tools.generateRandomArray(maxSize, maxValue);
            int[] arr2 = Tools.copyArr(arr1);
            int[] arr3 = Tools.copyArr(arr1);
            int[] arr4 = Tools.copyArr(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            Tools.comparator(arr4);
            if (!Tools.isEqual(arr1, arr2) || !Tools.isEqual(arr2, arr3) || !Tools.isEqual(arr3, arr4)) {
                Tools.printArray(arr1);
                System.out.println("=========");
                Tools.printArray(arr2);
                System.out.println("=========");
                Tools.printArray(arr3);
                System.out.println("=========");
                Tools.printArray(arr4);
                System.out.println("=========");
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!!" : "Fuck!!");
    }
}
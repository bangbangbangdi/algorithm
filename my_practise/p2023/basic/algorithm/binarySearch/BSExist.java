package my_practise.p2023.basic.algorithm.binarySearch;

import tool.Tools;

import java.util.Arrays;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.algorithm.binarySearch
 * className:      BSExist
 * author:     BangDi
 * description:  在有序数组下查询某数是否存在,存在则返回其中一个索引,不存在返回-1
 * date:    2023/5/26 00:45
 * version:    1.0
 */
public class BSExist {

    public static int binarySearchExist(int[] arr, int num) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L <= R) {
            int mid = L + (R - L) / 2;
            if (arr[mid] == num) {
                return mid;
            } else if (arr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return -1;
    }


    public static boolean BSExist(int[] arr, int num) {
        if (arr == null || arr.length < 1) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L <= R) {
            int mid = L + (R - L) / 2;
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return false;
    }

    public static boolean comparator(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        for (int i : arr) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int randomInt = Tools.getRandomInt(maxValue);
//            Tools.printArray(arr);
            if (BSExist(arr, randomInt) != comparator(arr, randomInt)) {
                Tools.printArray(arr);
                System.out.println(randomInt);
                System.out.println("BSExist " + BSExist(arr, randomInt));
                System.out.println("Comparator " + comparator(arr, randomInt));
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }
    public static void main(String[] args) {

        test();
    }

}

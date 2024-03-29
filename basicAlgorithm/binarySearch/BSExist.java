package basicAlgorithm.binarySearch;

import tool.Tools;

import java.util.Arrays;

public class BSExist {
    // 有序数组下,请找出某数是否存在
    public static boolean BSExist(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L < R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] < num) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return arr[L] == num;
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
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
    }
}

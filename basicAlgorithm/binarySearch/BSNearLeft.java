package basicAlgorithm.binarySearch;

import tool.Tools;

import java.util.Arrays;

public class BSNearLeft {

    // 有序数组下,请找出 >= num 的最左的数
    public static int BSNearLeft(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        int ans = -1;
        while (L < R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] >= num) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
//        ans = arr[L] >= num && L < ans ? L : ans;
        ans = arr[L] >= num? L : ans;
        return ans;
    }

    public static int comparator(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int ans = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= num) {
                return i;
            }
        }
        return ans;
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
            if (BSNearLeft(arr, randomInt) != comparator(arr, randomInt)) {
                succeed = false;
                Tools.printArray(arr);
                System.out.println(randomInt);
                System.out.println("BSExist " + BSNearLeft(arr, randomInt));
                System.out.println("Comparator " + comparator(arr, randomInt));
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
    }

}

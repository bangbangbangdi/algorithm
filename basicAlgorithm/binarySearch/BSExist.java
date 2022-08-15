package basicAlgorithm.binarySearch;

import tool.Tools;

import java.util.Arrays;

public class BSExist {

    public static boolean BSExist(int[] arr, int value) {
        if (arr == null) {
            return false;
        }
        int N = arr.length;
        boolean ans = false;
        int index = (N - 1) / 2;
        int pre = index;
        while (0 <= index && index < N) {
            System.out.println(index);
            if (arr[index] == value) {
                ans = true;
                break;
            } else if (arr[index] < value) {
                index += (N - index) / 2;
            } else {
                if (index == 0){
                    break;
                }
                index = index / 2;
            }
        }
        return ans;
    }

    public static boolean comparator(int[] arr, int value) {
        if (arr == null) {
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
            if (BSExist(arr,randomInt) != comparator(arr,randomInt)){
                Tools.printArray(arr);
                System.out.println("BSExist " + BSExist(arr,randomInt));
                System.out.println("Comparator " + comparator(arr,randomInt));
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
    }
}

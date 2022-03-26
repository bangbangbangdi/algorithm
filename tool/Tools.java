package tool;

import java.util.Arrays;

public class Tools {
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue,boolean isContainNegative) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        if (isContainNegative){
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            }
            return arr;
        }else{
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) ((maxValue + 1) * Math.random());
            }
            return arr;
        }
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void printStringArray(String[] str) {
        if (str == null){
            return;
        }
        for (int i = 0; i < str.length; i++) {
            System.out.print(str[i] + " ");
        }
        System.out.println();
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] copyArr(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static void swap(int[] arr, int i, int j) {
        if ((arr == null) || i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static int getRandomInt(int maxValue) {
        return (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
    }

    public static String generateRandomString(int strLen) {
        char[] chars = new char[(int) (strLen * Math.random() + 1)];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) ((int) (Math.random() * 6) + 97);
        }
        return String.valueOf(chars);
    }

    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] arr = new String[(int) (arrLen * Math.random() + 1)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = generateRandomString(strLen);
        }
        return arr;
    }
}

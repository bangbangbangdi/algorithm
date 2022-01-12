package myClass02;


import java.util.HashSet;
import java.util.Iterator;

public class Code06_BSAwesome {
    // arr 中的所有数相邻不等 找出一个局部最小值
    public static int binarySearchAwesome(int[] arr) {
        if ((arr == null) || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        int L = 1;
        int R = arr.length - 2;
        int index = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] < arr[mid-1] && arr[mid] < arr[mid+1]) {
                return mid;
            } else if (arr[mid] < arr[mid-1]){
                L = mid + 1;
            }else{
                R = mid - 1;
            }
        }
        return index;
    }

    public static boolean comparator(int[] arr, int index) {
        if ((arr == null) || (arr.length == 0)){
            return index == -1;
        }
        if (arr.length == 1){
            return 0 == index;
        }else if (index == 0){
            return arr[0]<arr[1];
        }else if (index == arr.length -1){
            return arr[arr.length-1] < arr[arr.length-2];
        }else{
            return arr[index]<arr[index-1] && arr[index] < arr[index+1];
        }

    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int size = (int) ((maxSize + 1) * Math.random());
        int[] arr = new int[size]; // 长度随机
        HashSet set = new HashSet();
        while(true){
            if (set.size() == size){
                break;
            }
            set.add((int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random()));
        }
        Iterator iterator = set.iterator();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) iterator.next();
        }
        return arr;
    }

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0~0 有序的
        // 0~i 想有序
        for (int i = 1; i < arr.length; i++) { // 0 ~ i 做到有序
            // arr[i]往前看，一直交换到合适的位置停止
            // ...(<=)  ?       <- i
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10; // 随机数组的长度0～100
        int maxValue = 100;// 值：-100～100
        int randomNum = (int) ((maxValue + 1) * Math.random());
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int index = binarySearchAwesome(arr);
            if (!comparator(arr,index)){
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
//        int[] ints = generateRandomArray(maxSize, maxValue);
//        printArray(ints);
//        int index = binarySearchAwesome(ints);
//        System.out.println(index);
//        System.out.println(ints[index]);
    }
}
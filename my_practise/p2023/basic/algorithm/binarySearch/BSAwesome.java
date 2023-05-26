package my_practise.p2023.basic.algorithm.binarySearch;

import tool.Tools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.binarySearch
 * className:      BSAwesome
 * author:     BangDi
 * description:  局部最小值:数组中每个元素左右不等,请找出局部最小值
 * date:    2023/5/26 9:44
 * version:    1.0
 */
public class BSAwesome {

    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        if (arr.length < 2 || arr[1] > arr[0]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int L = 1;
        int R = arr.length - 2;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return mid;
            }else if(arr[mid] < arr[mid - 1]){
                L = mid + 1;
            }else{
                R = mid - 1;
            }
        }
        return -1;
    }


    public static int getLessIndex1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return Integer.MAX_VALUE;
        }
        if (arr[1] > arr[0]) {
            return arr[0];
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr[arr.length - 1];
        }
        int L = 1;
        int R = arr.length - 2;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return arr[mid];
            }else if (arr[mid] < arr[mid - 1]){
                L = mid + 1;
            }else{
                R = mid - 1;
            }
        }
        return Integer.MAX_VALUE;
    }


    public static ArrayList<Integer> comparator(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (arr == null || arr.length == 0) {
            ans.add(-1);
            return ans;
        }
        int N = arr.length;
        if (N == 1) {
            ans.add(0);
            return ans;
        }
        if (arr[0] < arr[1]) {
            ans.add(0);
        }
        if (arr[N - 1] < arr[N - 2]) {
            ans.add(N - 1);
        }
        for (int i = 1; i < N - 1; i++) {
            if (arr[i] < arr[i - 1] && arr[i] < arr[i + 1]) {
                ans.add(i);
            }
        }
        if (ans.size() == 0) {
            ans.add(-1);
        }
        return ans;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int size = (int) ((maxSize + 1) * Math.random());
        int[] arr = new int[size]; // 长度随机
        HashSet set = new HashSet();
        while (true) {
            if (set.size() == size) {
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

    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
//            Tools.printArray(arr);
//            System.out.println("getLessIndex " + getLessIndex(arr));
            if (!comparator(arr).contains(getLessIndex(arr))) {
                succeed = false;
                Tools.printArray(arr);
                System.out.println("getLessIndex " + getLessIndex(arr));
                System.out.println("Comparator ");
                comparator(arr).forEach(System.out::print);
                System.out.println();
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");

    }

    public static void main(String[] args) {
        test();
    }

}

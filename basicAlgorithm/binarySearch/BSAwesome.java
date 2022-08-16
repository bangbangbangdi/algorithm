package basicAlgorithm.binarySearch;

import tool.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class BSAwesome {

    // 数组内的数相邻不等,请找出局部最小值的索引   如果数组不满足相邻不等,返回-1
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        if (arr.length == 1 || arr[L] < arr[L + 1]) {
            return L;
        }
        if (arr[R] < arr[R - 1]) {
            return R;
        }
        L = 1;
        R = R - 1;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
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
//        int[] arr = {-24,-24,-17,-47,80};
//        System.out.println(getLessIndex(arr));
//        ArrayList<Integer> comparator = comparator(arr);
//        comparator.forEach(System.out::println);
    }
}

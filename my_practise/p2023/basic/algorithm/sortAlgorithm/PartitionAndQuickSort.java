package my_practise.p2023.basic.algorithm.sortAlgorithm;

import tool.Tools;

import java.util.Arrays;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.sortAlgorithm
 * className:      PartitionAndQuickSort
 * author:     BangDi
 * description:  快排
 * date:    2023/5/23 18:28
 * version:    1.0
 */
public class PartitionAndQuickSort {

    // partition过程:给定一个数组arr，和一个整数num.请把小于num的数放在数组的左边，大于等于num的数放在数组的右边
    // 要求额外空间复杂度O(1),时间复杂度O(N)  本题中num为arr的最后一个元素 此外请返回等于该数的最小索引
    public static int partition(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int lessEqual = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[arr.length - 1]) {
                Tools.swap(arr, ++lessEqual, i);
            }
        }
        return lessEqual;
    }

    //荷兰国旗问题：给定一个数组arr，和一个整数num,请把小于num的数放在数组的左边,等于num的数放在中间,大于num的数放在数组的右边
    //要求额外空间复杂度O(1),时间复杂度O(N) 并且返回等于的最小和最大坐标
    public static int[] netherlandsFlag(int[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        int less = -1;
        int more = arr.length - 1;
        int num = arr[more];
        int i = 0;
        while (i < more) {
            if (arr[i] < num) {
                Tools.swap(arr, ++less, i++);
            } else if (arr[i] > num) {
                Tools.swap(arr, --more, i);
            } else {
                i++;
            }
        }
        Tools.swap(arr, arr.length - 1, more);
        return new int[]{++less, more};
    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int lessEqual = partition1(arr, L, R);
        process1(arr, L, lessEqual - 1);
        process1(arr, lessEqual, R);
    }

    public static int partition1(int[] arr, int L, int R) {
        int lessEqual = L - 1;
        for (int i = L; i <= R; i++) {
            if (arr[i] <= arr[R]) {
                Tools.swap(arr, ++lessEqual, i);
            }
        }
        //System.out.println("lessEqual = " + lessEqual);
        return lessEqual;
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] res = netherlandsFlag2(arr, L, R);
        int less = res[0];
        int more = res[1];
        process2(arr, L, less - 1);
        process2(arr, more + 1, R);
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        Tools.swap(arr, L, L + (int) (Math.random() * (R - L + 1)));
        int[] res = netherlandsFlag2(arr, L, R);
        process3(arr, L, res[0] - 1);
        process3(arr, res[1] + 1, R);
    }


    public static int[] netherlandsFlag2(int[] arr, int L, int R) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        //System.out.println("==================================");
        //Tools.printArray(arr);
        //System.out.println("L = " + L);
        //System.out.println("R = " + R);
        int less = L - 1;
        int more = R;
        int num = arr[more];
        int i = L;
        while (i < more) {
            if (arr[i] < num) {
                Tools.swap(arr, ++less, i++);
            } else if (arr[i] > num) {
                Tools.swap(arr, --more, i);
            } else {
                i++;
            }
        }
        Tools.swap(arr, R, more);
        //Tools.printArray(arr);
        //System.out.println("less = " + (less + 1));
        //System.out.println("more = " + more);
        //System.out.println("-------------------------------------");
        return new int[]{++less, more};
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue);
            int[] copy = Tools.copyArr(arr);
            quickSort3(arr);
            //Tools.printArray(copy);
            Arrays.sort(copy);
            if (!Tools.isEqual(arr, copy)) {
                Tools.printArray(arr);
                Tools.printArray(copy);
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {

        //-5 -6 -1 0 0 3
        //L = 0
        //R = 2
        //        -5 -6 3 0 0 -1
        //less = 2
        //more = 2
        //int[] arr = new int[]{-5,-6,-1,0,0,3};
        //int L = 0;
        //int R = 2;
        //int[] res = netherlandsFlag2(arr, L, R);
        //Tools.printArray(arr);
        //System.out.println(res[0] +" , " +res[1]);

        //int[] arr = Tools.generateRandomArray(100, 10);
        //Tools.printArray(arr);
        //quickSort2(arr);
        //Tools.printArray(arr);

        test();
    }
}

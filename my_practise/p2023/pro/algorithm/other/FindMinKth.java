package my_practise.p2023.pro.algorithm.other;

import tool.Tools;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm.other
 * className:      FindMinKth
 * author:     BangDi
 * description:
 * 在无序数组中求第K小第数
 * 0.堆排序 O(N * logK)
 * 1.改写快排的方法 O(N) 证明方法:求数学期望
 * 2.bfprt算法 O(N) 证明方法:T(N) = T(N/5) + T(7N/10)
 * date:    2023/7/26 00:01
 * version:    1.0
 */
public class FindMinKth {

    public static int minKth1(int[] arr, int k) {
        if (arr == null || arr.length < k || k < 1) {
            return Integer.MAX_VALUE;
        }
        Queue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < k; i++) {
            heap.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (heap.peek() > arr[i]) {
                heap.poll();
                heap.add(arr[i]);
            }
        }
        return heap.peek();
    }

    public static int minKth2(int[] arr, int k) {
        if (arr == null || arr.length < k || k < 1) {
            return Integer.MAX_VALUE;
        }
        return process(arr, 0, arr.length - 1, k - 1);
    }

    public static int process(int[] arr, int L, int R, int k) {
        int rand = L + (int) ((R - L + 1) * Math.random());
        int[] res = partition(arr, L, R, arr[rand]);
        if (k < res[0]) {
            return process(arr, L, res[0] - 1, k);
        } else if (k > res[1]) {
            return process(arr, res[1] + 1, R, k);
        }
        return arr[res[0]];
    }

    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int more = R + 1;
        int less = L - 1;
        int index = L;
        while (index < more) {
            if (arr[index] < pivot) {
                Tools.swap(arr, index++, ++less);
            } else if (arr[index] > pivot) {
                Tools.swap(arr, index, --more);
            } else {
                index++;
            }
        }
        return new int[]{++less, --more};
    }

    public static int minKth3(int[] arr, int k) {
        if (arr == null || arr.length < k || k < 1) {
            return Integer.MAX_VALUE;
        }
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }

    public static int bfprt(int[] arr, int L, int R, int k) {
        if (L == R) {
            return arr[L];
        }
        int pivot = medianOfMedians(arr, L, R);
        int[] res = partition(arr, L, R, pivot);
        if (k < res[0]) {
            return bfprt(arr, L, res[0] - 1, k);
        } else if (k > res[1]) {
            return bfprt(arr, res[1] + 1, R, k);
        }
        return arr[res[0]];
    }

    public static int medianOfMedians(int[] arr, int L, int R) {
        int offset = (R - L + 1) % 5 == 0 ? 0 : 1;
        int[] medians = new int[(R - L + 1) / 5 + offset];
        for (int i = 0; i < medians.length; i++) {
            int startIndex = L + i * 5;
            medians[i] = getMedian(arr, startIndex, Math.min(startIndex + 4, R));
        }
        return bfprt(medians, 0, medians.length - 1, medians.length / 2);
    }

    public static int getMedian(int[] arr, int L, int R) {
        insertSort(arr, L, R);
        return arr[(L + R) / 2];
    }

    public static void insertSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                Tools.swap(arr, j, j - 1);
            }
        }
    }

    public static int compare(int[] arr, int k) {
        if (arr == null || arr.length < k || k <= 0) {
            return Integer.MAX_VALUE;
        }
        Arrays.sort(arr);
        return arr[k - 1];
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 10;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue);
            int[] copy = Tools.copyArr(arr);
            int k = (int) ((arr.length + 1) * Math.random());
            int res = compare(arr, k);
            int res2 = minKth3(copy, k);
            if (res != res2) {
                Tools.printArray(arr);
                System.out.println("k = " + k);
                System.out.println("res = " + res);
                System.out.println("res2 = " + res2);
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }


    public static void main(String[] args) {

        int[] arr = {-5, -1, -2, 1, 1, 4, 8, 2, 5};
        int k = 6;
        int mid = -1;
//        partition(arr,6,7,-1);
//        System.out.println(bfprt(arr,5,8,5));
        test();
    }

}

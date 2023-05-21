package my_practise.p2022.basic.myClass04;


import tool.Tools;

import java.util.HashSet;
import java.util.PriorityQueue;

public class Code05_SortArrayDistanceLessK {

    public static void sortArrayDistanceLessK(int[] arr, int K) {
        if (arr == null || arr.length < 2) {
            return;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index < Math.min(arr.length - 1, K); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            arr[i] = heap.poll();
            heap.add(arr[index]);
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    public static int[] getArrayDistanceLessK(int maxSize, int maxValue, int K) {
        int[] arr = Tools.generateRandomArray(maxSize, maxValue);
        Tools.comparator(arr);
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(i);
        }
        int index = 0;
        while (!set.isEmpty() && (index < arr.length)) {
            int swapIndex = Math.min((int) (index + Math.random() * K), arr.length - 1);
            if (set.remove(arr[index]) && set.remove(arr[swapIndex])) {
                Tools.swap(arr, index, swapIndex);
            }
            index++;
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxValue = 100;
        int maxSize = 100;
        int K = 5;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = getArrayDistanceLessK(maxSize, maxValue, K);
            int[] copyArr = Tools.copyArr(arr);
            sortArrayDistanceLessK(arr, K);
            Tools.comparator(copyArr);
            if (!Tools.isEqual(arr, copyArr)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }
}
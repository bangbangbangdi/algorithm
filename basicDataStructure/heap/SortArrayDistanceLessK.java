package basicDataStructure.heap;

// 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
// 请选择一个合适的排序策略，对这个数组进行排序。

import tool.Tools;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SortArrayDistanceLessK {

    public static void sortArrDistanceLessK(int[] arr, int k) {
        if (arr == null || arr.length == 1) {
            return;
        }
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index <= Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    public static void heapIfy(int[] arr, int index, int size) {
        int leftIndex = index * 2 + 1;
        while (leftIndex < size) {
            int largestIndex = (leftIndex + 1 < size) && arr[leftIndex + 1] > arr[leftIndex] ? leftIndex + 1 : leftIndex;
            largestIndex = arr[largestIndex] > arr[index] ? largestIndex : index;
            if (index == largestIndex) {
                return;
            }
            Tools.swap(arr, index, largestIndex);
            index = largestIndex;
            leftIndex = index * 2 + 1;
        }
    }


    public static void main(String[] args) {
        int[] arr = {2, 3, 0, 1, 4, 5, 6, 9, 8, 7};
        int k = 2;
        sortArrDistanceLessK(arr, k);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}

package my_practise.p2023.basic.datastructure.heap;

import tool.Tools;

import java.util.PriorityQueue;


/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.heap
 * className:      SortArrayDistanceLessK
 * author:     BangDi
 * description:
 * // 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
 * // 请选择一个合适的排序策略，对这个数组进行排序。
 * date:    2023/6/14 16:04
 * version:    1.0
 */
public class SortArrayDistanceLessK {

    public static void sortArrDistanceLessK(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (int i = 0; i <= Math.min(arr.length, k); i++) {
            heap.add(i);
        }
        for (int i = k + 1; i < arr.length; i++) {
            arr[index++] = heap.poll();
            heap.add(i);
        }
        while (!heap.isEmpty()) {
            arr[index++] = heap.poll();
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
package basicDataStructure.heap;

// 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
// 请选择一个合适的排序策略，对这个数组进行排序。

import tool.Tools;

import java.util.Arrays;

public class SortArrayDistanceLessK {

    public static void sortArrDistanceLessK(int[] arr, int K) {
        if (arr == null || arr.length == 1) {
            return;
        }
        int N = arr.length - 1;
        for (int i = N; i > N - K; i--) {
            heapIfy(arr, i, N + 1);
        }
        for (int i = N - K + 1; i >= 0; i--) {
            heapIfy(arr, i, i + K);
            Tools.swap(arr, i, i + K);
        }
        for (int i = K; i >= 0; i--) {
            heapIfy(arr,i,i);
            Tools.swap(arr,0,i);
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

    }
}

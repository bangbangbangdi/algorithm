package basicDataStructure.heap;

import tool.Tools;

public class SortArrayDistanceLessK {

    public static void HeapSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }

    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] < arr[(index - 1) / 2]) {
            Tools.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapIfy(int[] arr, int index, int size) {
        int leftIndex = index * 2 + 1;
        while(leftIndex < size){

        }
    }

}

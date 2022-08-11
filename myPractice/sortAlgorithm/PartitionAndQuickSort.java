package myPractice.sortAlgorithm;

import tool.Tools;

public class PartitionAndQuickSort {

    // 把<=arr[R]的数放左边,其余的数放右边,并且返回边界的坐标,下坐标
    public static int partition(int[] arr, int L, int R) {
        if (L >= R) {
            return -1;
        }
        int lessIndex = -1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                Tools.swap(arr, ++lessIndex, index);
            }
            index++;
        }
        return lessIndex;
    }

    public static void test() {
        int[] arr = Tools.generateRandomArray(10, 10);
        Tools.printArray(arr);
        System.out.println(partition(arr, 0, arr.length - 1));
        Tools.printArray(arr);
    }

    public static void main(String[] args) {
        test();
    }
}

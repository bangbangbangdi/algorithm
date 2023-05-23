package my_practise.p2023.basic.algorithm.sortAlgorithm;

import tool.Tools;

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

    // partition过程:给定一个数组arr，和一个整数num.请把小于等于num的数放在数组的左边，大于等于num的数放在数组的右边
    // 要求额外空间复杂度O(1),时间复杂度O(N)  本题中num为arr的最后一个元素
    public static void partition(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
           if (arr[i] <= arr[arr.length - 1]){
               Tools.swap(arr,++index,i);
           }
        }
    }

    //荷兰国旗问题：给定一个数组arr，和一个整数num,请把小于num的数放在数组的左边,等于num的数放在中间,大于num的数放在数组的右边
    //要求额外空间复杂度O(1),时间复杂度O(N)
    public static void netherlandsFlag(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int
    }

    public static void main(String[] args) {
        int[] arr = Tools.generateRandomArray(100, 10);
        Tools.printArray(arr);
        partition(arr);
        Tools.printArray(arr);
    }

}

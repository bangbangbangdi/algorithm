package my_practise.p2023.basic.algorithm.sortAlgorithm;

import tool.Tools;

import java.util.Arrays;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.algorithm.sortAlgorithm
 * className:      CountSort
 * author:     BangDi
 * description:  计数排序
 * date:    2023/5/23 00:48
 * version:    1.0
 */
public class CountSort {

    // 计数排序适用于样本范围不大的场景 例如人的年纪之类的 本例子中默认数组中不存在负数
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 第一步先求出最大值(需要多少个桶)
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
        }
        // 按最大值创建对应的桶
        int[] bucket = new int[max + 1];
        // 往桶内装入对应多的元素
        for (int i : arr) {
            bucket[i]++;
        }
        // 将桶国内元素倒出来
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- != 0) {
                arr[index++] = i;
            }
        }
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue, false);
            int[] copy = Tools.copyArr(arr);
            countSort(arr);
            Arrays.sort(copy);
            if (!Tools.isEqual(arr,copy)){
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
    }
}

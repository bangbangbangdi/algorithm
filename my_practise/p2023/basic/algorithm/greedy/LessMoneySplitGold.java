package my_practise.p2023.basic.algorithm.greedy;

import tool.Tools;

import java.util.*;
import java.util.stream.Collectors;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.algorithm.greedy
 * className:      LessMoneySplitGold
 * author:     BangDi
 * description:
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。
 * 比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
 * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
 * 如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;一共花费110铜板。
 * 但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30;一共花费90铜板。
 * 输入一个数组，返回分割的最小代价。
 * date:    2023/6/2 00:39
 * version:    1.0
 */
public class LessMoneySplitGold {

    public static int lms(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i : arr) {
            heap.add(i);
        }
        int res = 0;
        while (heap.size() >= 2) {
            int sum = heap.poll() + heap.poll();
            res += sum;
            heap.add(sum);
        }
        return res;
    }

    // 这种看似正确的做法是错误的
    public static int testLms(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = Arrays.stream(arr).sum();
        int res = sum;
        Arrays.sort(arr);
        for (int i = arr.length - 1; i > 1; i--) {
            sum -= arr[i];
            res += sum;
        }
        return res;
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 10;
        int maxValue = 10;
        int minValue = 1;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue,minValue);
            int compare = testLms(arr);
            int less = lms(arr);
            if (compare != less) {
                succeed = false;
                Tools.printArray(arr);
                System.out.println("compare: " + compare);
                System.out.println("less: " + less);
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }


    public static void main(String[] args) {
        //test();
        int[] arr = {6, 7, 8,10};
        System.out.println(lms(arr));
        System.out.println(testLms(arr));
    }

}

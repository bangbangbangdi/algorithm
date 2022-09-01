package basicAlgorithm.greedy;

//一块金条切成两半，是需要花费和长度数值一样的铜板的。
//比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
//例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
//如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;一共花费110铜板。
//但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30;一共花费90铜板。
//输入一个数组，返回分割的最小代价。

import tool.Tools;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LessMoneySplitGold {

    public static int less(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        int cost = 0;
        for (int i = arr.length; i > 1; i--) {
            cost += Arrays.stream(arr, 0, i).sum();
        }
        return cost;
    }

    public static int compare(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int cur = Arrays.stream(arr, left, right + 1).sum();
        int remain = Integer.MAX_VALUE;
        for (int index = left; index < right; index++) {
            remain = Math.min(remain, process(arr, left, index) + process(arr, index + 1, right));
        }
        return cur + (remain == Integer.MAX_VALUE ? 0 : remain);
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue) * Math.random() + 1);
        }
        return arr;
    }

    public static int lessMoney2(int[] arr) {
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (pQ.size() > 1) {
            cur = pQ.poll() + pQ.poll();
            sum += cur;
            pQ.add(cur);
        }
        return sum;
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 10;
        int maxValue = 10;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
//            int[] copyArr = Tools.copyArr(arr);
//            Tools.printArray(arr);
            int compare = compare(arr);
//            int less = less(arr);
            int less2 = lessMoney2(arr);
            if (compare != less2) {
                succeed = false;
                Tools.printArray(arr);
                System.out.println("compare: " + compare);
//                System.out.println("less: " + less);
                System.out.println("less2: " + less2);
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
//        int[] arr = {2,4,1,1};
//        System.out.println("compare" + compare(arr));
//        System.out.println("less" + less(arr));

    }
}
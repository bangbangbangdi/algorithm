package basicAlgorithm.greedy;

//一块金条切成两半，是需要花费和长度数值一样的铜板的。
//比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
//例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
//如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;一共花费110铜板。
//但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30;一共花费90铜板。
//输入一个数组，返回分割的最小代价。

//该题目的精髓在于需要把握住,从大往小拆分的花销和从小组合到大的花销是一致的
//如何把一个数组以最小的成本组合到一起呢？
//数组第一小的和数组第二小的组合
//然后组合后的数字重新加入数组中进行排序,再重复上一步
//堆无疑能很方便的实现上述功能
//不过这这种解法依然有个含混的地方,例如如果遇上 [0,0,0,20]
//这种类型的数组,那么最后所得出的结果会是20,而实际上应该是0

import tool.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class LessMoneySplitGold {

    public static int less(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i : arr) {
            heap.add(i);
        }
        int res = 0;
        while (heap.size() > 1) {
            int sum = heap.poll() + heap.poll();
            res += sum;
            heap.add(sum);
        }
        return res;
    }

    // 排序 + 任意顺序切割 虽然不知道为啥是正确的,不过确实能通过对数器
    // 有可能的解释是,排序行为是最小方案的前提,在满足前提的情况下,再对切割行为进行全排列就能得出答案...
    public static int compare(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L >= R) {
            return 0;
        }
        int cur = Arrays.stream(arr, L, R + 1).sum();
        int remain = Integer.MAX_VALUE;
        for (int index = L; index < R; index++) {
            remain = Math.min(remain, process(arr, L, index) + process(arr, index + 1, R));
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


    public static void test() {
        int testTime = 1000000;
        int maxSize = 10;
        int maxValue = 10;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int compare = compare(arr);
            int less = less(arr);
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
        test();
//        int[] arr = {1, 2, 3};
//        System.out.println(less(arr));
//        System.out.println(compare(arr));
    }
}
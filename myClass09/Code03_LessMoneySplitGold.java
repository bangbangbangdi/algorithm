package myClass09;

import tool.Tools;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Code03_LessMoneySplitGold {

    public static int lessMoney1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }

    public static int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
            }
        }

        return ans;
    }

    public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int ansIndex = 0;
        for (int k = 0; k < arr.length; k++) {
            if (k != i && k != j) {
                ans[ansIndex++] = arr[k];
            }
        }
        ans[ansIndex] = arr[i] + arr[j];
        return ans;
    }

    public static int lessMoney2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        int cur = 0;
        PriorityQueue<Integer> stack = new PriorityQueue<>();

        for (int i = 0; i < arr.length; i++) {
            stack.offer(arr[i]);
        }
        while (stack.size() > 1) {
            cur = stack.poll() + stack.poll();
            sum += cur;
            stack.offer(cur);
        }
        return sum;
    }

    // 错误，这个问题并不是简单的先切最大那部分就可以
    public static int lessMoney3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] trieTree = getTrieTree(arr);
        int lessMoney = 0;
        for (int i = trieTree.length - 1; i > 0; i--) {
            lessMoney += trieTree[i];
        }
        return lessMoney;
    }

    public static int[] getTrieTree(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return null;
        }
        int[] trieTree = new int[arr.length];
        Arrays.sort(arr);
        trieTree[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            trieTree[i] = trieTree[i - 1] + arr[i];
        }
        return trieTree;
    }


    public static void main(String[] args) {
        int testTimes = 500000;
        int maxValue = 10;
        int maxSize = 6;
        boolean succeed = true;
        for (int i = 0; i < testTimes && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue);
            Tools.printArray(arr);
            if (lessMoney1(arr) != lessMoney2(arr) || lessMoney1(arr) != lessMoney3(arr)) {
                System.out.println("less1 " + lessMoney1(arr));
                System.out.println("less2 " + lessMoney2(arr));
                System.out.println("less3 " + lessMoney3(arr));
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "F");

//        int[] arr = new int[]{10,20,30};
//        System.out.println(lessMoney3(arr));
    }
}
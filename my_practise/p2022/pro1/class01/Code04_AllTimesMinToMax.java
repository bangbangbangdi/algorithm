package my_practise.p2022.pro1.class01;

import tool.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


// 给定一个只包含正整数的数组arr,arr中任何一个子数组sub,
// 一定都可以算出(sub累加和) * (sub中的最小值) 是什么,
// 那么所有子数组中,这个值最大是多少?
public class Code04_AllTimesMinToMax {

    public static int max1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return Integer.MIN_VALUE;
        }
        Stack<List<Integer>> stack = new Stack<>();
        int res = 0;
        int help[][] = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> indexList = stack.pop();
                for (Integer index : indexList) {
                    help[index][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    help[index][1] = i;
                }
            }
            if (stack.isEmpty() || arr[stack.peek().get(0)] != arr[i]) {
                ArrayList<Integer> pushList = new ArrayList<>();
                pushList.add(i);
                stack.push(pushList);
            } else {
                stack.peek().add(i);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> indexList = stack.pop();
            for (Integer index : indexList) {
                help[index][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                help[index][1] = -1;
            }
        }
        for (int i = 0; i < help.length; i++) {
            System.out.println("i = " + i);
            System.out.println("help[i][0] = " + help[i][0]);
            res = Math.max(res, arr[i] * Arrays.stream(arr, help[i][0] + 1, help[i][1] == -1 ? help.length : help[i][1]).reduce(0, Integer::sum));
        }
        return res;
    }

    // 这里的优化是将原本的流处理换优化为前缀和数组
    public static int max2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return Integer.MIN_VALUE;
        }
        Stack<List<Integer>> stack = new Stack<>();
        int res = 0;
        int help[][] = new int[arr.length][2];
        int sum[] = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> indexList = stack.pop();
                for (Integer index : indexList) {
                    help[index][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    help[index][1] = i;
                }
            }
            if (stack.isEmpty() || arr[stack.peek().get(0)] != arr[i]) {
                ArrayList<Integer> pushList = new ArrayList<>();
                pushList.add(i);
                stack.push(pushList);
            } else {
                stack.peek().add(i);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> indexList = stack.pop();
            for (Integer index : indexList) {
                help[index][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                help[index][1] = -1;
            }
        }
        for (int i = 0; i < help.length; i++) {
            res = Math.max(res, arr[i] * (sum[help[i][1] == -1 ? sum.length - 1 : help[i][1] - 1] - (help[i][0] == -1 ? 0 : sum[help[i][0]])));
        }
        return res;
    }

    // 这里的优化是将原本单调栈类型从List类型换成了Integer类型
    // 原因在于,就算数组中有重复值,也不影响本问题的求解
    // 此外将原有的help数组也删除了
    // 因为:获取最大值的操作可以在每次栈弹出元素时去更新
    public static int max3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return Integer.MIN_VALUE;
        }
        Stack<Integer> stack = new Stack<>();
        int res = Integer.MIN_VALUE;
        int sum[] = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                Integer index = stack.pop();
                res = Math.max(res, arr[index] * (sum[i - 1] - (stack.isEmpty() ? 0 : sum[stack.peek()])));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer index = stack.pop();
            res = Math.max(res, arr[index] * (sum[sum.length - 1] - (stack.isEmpty() ? 0 : sum[stack.peek()])));
        }
        return res;
    }

    public static int compare(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    public static void test(){
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        System.out.println("test begin");
        for (int i = 0;success && i < testTime; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue,false);
            int ans1 = max3(arr);
            int ans2 = compare(arr);
            if (ans1 != ans2) {
                success = false;
                Tools.printArray(arr);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
            }
        }
        System.out.println(success ? "Nice" : "F");
    }

    public static void main(String[] args) {
//        int[] arr = {1, 3, 1, 3, 0};
//        int[][] res = max1(arr);
//        for (int[] re : res) {
//            System.out.print(Arrays.toString(re) + " ");
//        }
//        System.out.println(Arrays.stream(arr, 2, 2).reduce(0, Integer::sum));
//        System.out.println(max3(arr));
        test();
    }

}
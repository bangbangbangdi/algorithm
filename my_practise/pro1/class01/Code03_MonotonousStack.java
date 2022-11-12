package my_practise.pro1.class01;

import tool.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

// 实现 有/无 重复值的情况下,单调栈的生成(单调栈如下)
// 一种特别设计的栈结构,为了解决如下的问题
// 给定一个可能含有重复值的数组arr,i位置的数一定存在如下两个信息
// 1.arr[i]的左侧离i最近并且小于(或大于)arr[i]的数在哪?
// 2.arr[i]的右侧离i最近并且小于(或大于)arr[i]的数在哪?
// 如果想要得到arr中所有位置的两个信息,怎么能让得到的信息的过程尽量快
public class Code03_MonotonousStack {

    // 单调栈,顾名思义,即栈内元素都是具备单调性的
    public static int[][] getNearLessNoRepeat(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer popIndex = stack.pop();
                res[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer popIndex = stack.pop();
            res[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][1] = -1;
        }
        return res;
    }

    public static int[][] getNearLess(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popList = stack.pop();
                for (Integer index : popList) {
                    res[index][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    res[index][1] = i;
                }
            }
            if (stack.isEmpty() || arr[stack.peek().get(0)] != arr[i]) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            } else {
                List<Integer> list = stack.peek();
                list.add(i);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> popList = stack.pop();
            for (Integer index : popList) {
                res[index][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                res[index][1] = -1;
            }
        }
        return res;
    }


    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    public static void test() {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 9;
        boolean success = true;
        for (int i = 0; success && i < testTime; i++) {
            int[] arr = Tools.getRandomArray(maxSize, maxValue);
//            System.out.println("w = " + w);
//            Tools.printArray(arr);
            int[][] ans1 = getNearLess(arr);
            int[][] ans2 = rightWay(arr);
            if (!isEqual(ans1, ans2)) {
                success = false;
                Tools.printArray(arr);
                System.out.println("ans1");
                for (int[] re : ans1) {
                    Tools.printArray(re);
                }
                System.out.println("ans2");
                for (int[] re : ans2) {
                    Tools.printArray(re);
                }
            }
        }
        System.out.println(success ? "Nice" : "F");
    }

    public static void main(String[] args) {
        test();
//        int[] arr = {1, 3, 1, 3, 0};
//        int[][] res = getNearLess(arr);
//        int[][] res2 = rightWay(arr);
//        for (int[] re : res) {
//            System.out.print("" + Arrays.toString(re));
//        }
//        System.out.println("-----------");
//        for (int[] re : res2) {
//            System.out.print("" + Arrays.toString(re));
//        }
    }

}

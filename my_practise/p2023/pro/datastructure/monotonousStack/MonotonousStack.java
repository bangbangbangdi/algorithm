package my_practise.p2023.pro.datastructure.monotonousStack;

import java.util.Stack;

/**
 * @description 单调栈是什么?
 * 一种特别设计的栈结构，为了解决如下的问题：
 * 给定一个可能含有重复值的数组arr，i位置的数一定存在如下两个信息
 * 1）arr[i]的左侧离i最近并且小于(或者大于)arr[i]的数在哪？
 * 2）arr[i]的右侧离i最近并且小于(或者大于)arr[i]的数在哪？
 * 如果想得到arr中所有位置的两个信息，怎么能让得到信息的过程尽量快。
 * <p>
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 * @date 2023/6/27 23:06
 */
public class MonotonousStack {

    public static int[][] getNearLessNoRepeat(int[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        int index = 1;
        stack.push(0);
        while (!stack.isEmpty()) {
            while (!stack.isEmpty() && (index == arr.length || arr[stack.peek()] > arr[index])) {
                Integer i = stack.pop();
                res[i][0] = stack.isEmpty() ? -1 : stack.peek();
                res[i][1] = index == arr.length ? -1 : index;
            }
            if (index < arr.length){
                stack.push(index);
                index++;
            }
        }
        return res;
    }

    public static int[][] getNearLess(int[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> tmp = new Stack<>();
        int index = 1;
        stack.push(0);
        while (!stack.isEmpty()) {
            while (!stack.isEmpty() && (index == arr.length || arr[stack.peek()] > arr[index])) {
                tmp.push(stack.pop());
                while (!stack.isEmpty() && arr[tmp.peek()] == arr[stack.peek()]) {
                    tmp.push(stack.pop());
                }
                while (!tmp.isEmpty()) {
                    Integer i = tmp.pop();
                    res[i][0] = stack.isEmpty() ? -1 : stack.peek();
                    res[i][1] = index == arr.length ? -1 : index;
                }
            }
            if (index < arr.length) {
                stack.push(index);
                index++;
            }
        }
        return res;
    }

    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
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

    // for test
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

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
            if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
                System.out.println("Oops!");
                printArray(arr1);
                break;
            }
            if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("Oops!");
                printArray(arr2);
                break;
            }
        }
        System.out.println("finish");
    }
    //public static void main(String[] args) {
    //    int[] arr = {6, 0, 2, 7, 8, 9, 3};
    //    //int[][] ans = getNearLess(arr);
    //    int[][] ans = getNearLessNoRepeat(arr);
    //    for (int[] an : ans) {
    //        for (int i : an) {
    //            System.out.print(i + " ");
    //        }
    //        System.out.println();
    //    }
    //}

}

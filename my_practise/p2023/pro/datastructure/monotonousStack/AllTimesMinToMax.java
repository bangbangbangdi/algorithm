package my_practise.p2023.pro.datastructure.monotonousStack;

import tool.Tools;

import java.util.Stack;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.pro.datastructure.monotonousStack
 * className:      AllTimesMinToMax
 * author:     BangDi
 * description:
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 * date:    2023/6/28 11:17
 * version:    1.0
 */
public class AllTimesMinToMax {

    public static int max1(int[] arr) {
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
    // 这道题没有必要关心数组内是否有重复的数,因为我们并不是要求单调栈的结果
    // 而多个存在多个重复数同时压栈的情况下,最底下的数是正确的(我赌你看不懂...)
    public static int max2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return Integer.MIN_VALUE;
        }
        int max = Integer.MIN_VALUE;
        int index = 1;
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        // 这里由于不需要得到单调栈的结果因此可以省去建立额外的结构
        //   这也是一大类优化的思路,如果最终的结果可以跟随整个过程求出,那么中间的结果可以只存储到变量中;
        //   这里就是max    2024.2.4
        while (!stack.isEmpty()) {
            while (!stack.isEmpty() && (index == arr.length || arr[stack.peek()] > arr[index])) {
                Integer i = stack.pop();
                max = Math.max(max, arr[i] * (sum[index - 1] - (stack.isEmpty() ? 0 : sum[stack.peek()])));
            }
            if (index < arr.length) {
                stack.push(index);
                index++;
            }
        }
        return max;
    }



    public static void main(String[] args) {
        int maxSize = 10;
        int maxValue = 100;
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = Tools.generateRandomArray(maxSize,maxValue,false);
            if (max1(arr) != max2(arr)) {
                Tools.printArray(arr);
                System.out.println(max1(arr));
                System.out.println(max2(arr));
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
//        int[] arr = {5,6,3,6};
//        System.out.println(max2(arr));
    }
}
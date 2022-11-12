package my_practise.pro1.class01;

import tool.Tools;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code01_SlidingWindowMaxArray {
    // 假设一个固定大小为W的窗口,依次划过arr
    // 返回每一次滑出状况的最大值
    // 例如 arr [4,3,5,4,3,3,6,7] W = 3
    // 返回 [5,5,5,4,6,7]
    public static int[] getMaxWindow(int[] arr, int W) {
        if (arr == null || arr.length < 1 || W <= 0) {
            return null;
        }
        int[] res = new int[arr.length - W + 1];
        LinkedList<Integer> maxQ = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (!maxQ.isEmpty() && arr[maxQ.peekLast()] < arr[i]) {
                maxQ.pollLast();
            }
            maxQ.addLast(i);
            if (i - W == maxQ.getFirst()) {
                maxQ.pollFirst();
            }
            if (i - W + 1 >= 0) {
                res[i - W + 1] = arr[maxQ.getFirst()];
            }
        }
        return res;
    }

    public static int[] rightWay(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < arr.length) {
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);
            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }



    public static void test(){
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        System.out.println("test begin");
        for (int i = 0;success && i < testTime; i++) {
            int[] arr = tool.Tools.generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
//            System.out.println("w = " + w);
//            Tools.printArray(arr);
            int[] ans1 = getMaxWindow(arr, w);
            int[] ans2 = rightWay(arr, w);
            if (!tool.Tools.isEqual(ans1, ans2)) {
                success = false;
                Tools.printArray(arr);
                Tools.printArray(ans1);
                Tools.printArray(ans2);
            }
        }
        System.out.println(success ? "Nice" : "F");
    }

    public static void main(String[] args) {
        test();
//        int[] arr = {66,23,27,-2};
//        int[] maxWindow = getMaxWindow(arr, 3);
//        for (int i : maxWindow) {
//            System.out.print(i + " ");
//        }
    }


}

package my_practise.p2023.pro.datastructure.SildingWindow;

/**
 * 假设一个固定大小为W的窗口，依次划过arr，
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 * @date 2023/6/23 9:27
 */

import tool.Tools;

import java.util.LinkedList;

public class SlidingWindowMaxArray {

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || arr.length < 1 || w < 1 || arr.length - w < 0) {
            return null;
        }
        int[] ans = new int[arr.length - w + 1];
        LinkedList<Integer> maxQ = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (!maxQ.isEmpty() && arr[maxQ.peekLast()] < arr[i]) {
                maxQ.pollLast();
            }
            maxQ.addLast(i);
            if (i - w == maxQ.peekFirst()) {
                maxQ.pollFirst();
            }
            if (i - w + 1 >= 0) {
                ans[i - w + 1] = arr[maxQ.peekFirst()];
            }
        }
        return ans;
    }

    // for test
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

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxWindow(arr, w);
            int[] ans2 = rightWay(arr, w);
            if (!isEqual(ans1, ans2)) {
                Tools.printArray(arr);
                System.out.println("-----");
                Tools.printArray(ans1);
                Tools.printArray(ans2);
                System.out.println("w " + w);
                System.out.println("Oops!");
                succeed = false;
            }
        }
        System.out.println("test finish");
    }
}

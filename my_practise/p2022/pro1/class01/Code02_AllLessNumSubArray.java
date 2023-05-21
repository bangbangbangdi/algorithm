package my_practise.p2022.pro1.class01;


import tool.Tools;

import java.util.Arrays;
import java.util.LinkedList;

// 给定一个整型数组arr,和一个整数num
// 某个arr中的子数组sub,如果想达标,必须满足:
// sub中最大值 - sub中最小值 <= num
// 返回arr中达标子数组的数量
public class Code02_AllLessNumSubArray {

    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0){
            return 0;
        }
        int res = 0;
        int L = 0;
        int R = 0;
        LinkedList<Integer> max_q = new LinkedList<>();
        LinkedList<Integer> min_q = new LinkedList<>();
        while (L < arr.length){
            while(R < arr.length){
                while(!max_q.isEmpty() && arr[max_q.peekLast()] <= arr[R]){
                    max_q.pollLast();
                }
                max_q.addLast(R);
                while(!min_q.isEmpty() && arr[min_q.peekLast()] >= arr[R]){
                    min_q.pollLast();
                }
                min_q.addLast(R);
                if (arr[max_q.peekFirst()] - arr[min_q.peekFirst()] > num){
                    break;
                }
                R++;
            }
            if (L == min_q.peekFirst()){
                min_q.pollFirst();
            }
            if (L == max_q.peekFirst()){
                max_q.pollFirst();
            }
            res += R - L;
            L++;
        }
        return res;
    }

    public static int compare(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = 0;
        for (int L = 0; L < arr.length; L++) {
            for (int R = L; R < arr.length; R++) {
                res = isTarget(arr, L, R, num) ? res + 1 : res;
            }
        }
        return res;
    }

    public static boolean isTarget(int[] arr, int L, int R, int num) {
        if (arr == null || arr.length == 0 || L > R || L < 0 || R >= arr.length) {
            return false;
        }
        int index = 0;
        int[] temp = new int[R - L + 1];
        for (int i = L; i <= R; i++) {
            temp[index++] = arr[i];
        }
        Arrays.sort(temp);
        int min = temp[0];
        int max = temp[temp.length - 1];
        return max - min <= num;
    }

    public static void test() {
        int testTime = 10000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        System.out.println("test begin");
        for (int i = 0; success && i < testTime; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue);
            int num = (int) (Math.random() * (arr.length + 1));
//            System.out.println("num = " + num);
//            Tools.printArray(arr);
//            System.out.println("i = " + i);
            int ans1 = getNum(arr, num);
            int ans2 = compare(arr, num);
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
        test();
//        int[] arr = {3,2,1,5,4,1};
//        System.out.println(getNum(arr, 2));
//        System.out.println(compare(arr,2));
    }

}

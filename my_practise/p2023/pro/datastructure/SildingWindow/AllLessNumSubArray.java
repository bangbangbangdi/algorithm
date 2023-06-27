package my_practise.p2023.pro.datastructure.SildingWindow;

import java.util.LinkedList;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.pro.datastructure.SildingWindow
 * className:      AllLessNumSubArray
 * author:     BangDi
 * description:
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 * date:    2023/6/23 9:29
 * version:    1.0
 */
public class AllLessNumSubArray {

    public static int allLessNumSubArr(int[] arr, int num) {
        if (arr == null || arr.length < 1 || num < 0) {
            return 0;
        }
        int L = 0;
        int R = 0;
        int res = 0;
        LinkedList<Integer> maxQ = new LinkedList<>();
        LinkedList<Integer> minQ = new LinkedList<>();
        while (L < arr.length) {
            while (R < arr.length) {
                while(!maxQ.isEmpty() && arr[maxQ.peekLast()] <= arr[R]){
                    maxQ.pollLast();
                }
                maxQ.offerLast(R);
                while(!minQ.isEmpty() && arr[minQ.peekLast()] >= arr[R]){
                    minQ.pollLast();
                }
                minQ.offerLast(R);
                if (arr[maxQ.peekFirst()] - arr[minQ.peekFirst()] > num){
                    break;
                }
                R++;
            }
            res += R - L;
            if (maxQ.peekFirst() == L){
                maxQ.pollFirst();
            }
            if (minQ.peekFirst() == L){
                minQ.pollFirst();
            }
            L++;
        }
        return res;
    }

    public static void main(String[] args) {
        //int[] arr = {2,2,2,10,2,2,2};
        int[] arr = {3, 2, 1, 5, 4, 1};
        int num = 2;
        System.out.println(allLessNumSubArr(arr, num));
    }

}
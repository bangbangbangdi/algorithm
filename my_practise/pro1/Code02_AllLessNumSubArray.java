package my_practise.pro1;

import java.util.LinkedList;

// 给定一个整型数组arr,和一个整数num
// 某个arr中的子数组sub,如果想达标,必须满足:
// sub中最大值 - sub中最小值 <= num
// 返回arr中达标子数组的数量
public class Code02_AllLessNumSubArray {

    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = 0;
        int L = 0;
        int R = 0;
        LinkedList<Integer> min_q = new LinkedList<>();
        LinkedList<Integer> max_q = new LinkedList<>();
        while(L < arr.length){
            while(R < arr.length){
                while(!min_q.isEmpty() && arr[min_q.peekLast()] > arr[R]){
                    min_q.pollLast();
                }
                min_q.addLast(R);
                while(!max_q.isEmpty() && arr[max_q.peekLast()] < arr[R]){
                    max_q.pollLast();
                }
                max_q.addLast(R);
                if (arr[max_q.peekFirst()] - arr[min_q.peekFirst()] > num){
                    break;
                }
                R++;
            }
            if (L == min_q.peekFirst()){
                min_q.pollFirst();
            }
            if (R == max_q.peekFirst()){
                max_q.pollFirst();
            }
            res += R - L;
            L++;
        }


        return res;
    }

}

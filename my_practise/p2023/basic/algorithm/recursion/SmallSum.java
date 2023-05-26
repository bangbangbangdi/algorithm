package my_practise.p2023.basic.algorithm.recursion;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.recursion
 * className:      SmallSum
 * author:     BangDi
 * description:  求数组小和
 * date:    2023/5/26 16:04
 * version:    1.0
 */
//在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
//例子: [1,3,4,2,5]
//1左边比1小的数：没有
//3左边比3小的数：1
//4左边比4小的数：1、3
//2左边比2小的数：1
//5左边比5小的数：1、3、4、 2
//所以数组的小和为1+1+3+1+1+3+4+2=16
public class SmallSum {

    // 思路: 归并排序!!在归并的过程中算出最小和
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
    }

    public static int process(int[] arr, int L, int R) {
        if (L >= R) {
            return 0;
        }
        int mid = L + ((R - L + 1) >> 1);
        int res = 0;
        res += process(arr, L, mid - 1);
        res += process(arr, mid + 1, R);
        res += merge(arr, L, R, mid);
        return res;
    }

    public static int merge(int[] arr, int L, int R, int mid) {
        int res = 0;
        int p = L;
        int p1 = mid + 1;
        int[] help = new int[R - L + 1];
        int index = 0;
        while (p <= mid && p1 <= R) {
            res += arr[p] < arr[p1] ? arr[p] * (R - p1 + 1) : 0;
            help[index++] = arr[p] < arr[p1] ? arr[p++] : arr[p1++];
        }
        while(p <= mid){
            help[index++] = arr[p++];
        }
        while(p1 <= R){
            help[index++] = arr[p1++];
        }

        return res;
    }

}

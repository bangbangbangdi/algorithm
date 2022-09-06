package basicAlgorithm.recursion;

//在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
//例子: [1,3,4,2,5]
//1左边比1小的数：没有
//3左边比3小的数：1
//4左边比4小的数：1、3
//2左边比2小的数：1
//5左边比5小的数：1、3、4、 2
//所以数组的小和为1+1+3+1+1+3+4+2=16

import tool.Tools;

public class SmallSum {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L >= R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        int res = 0;
        res += process(arr, L, M);
        res += process(arr, M + 1, R);
        res += merge(arr, L, R, M);
        return res;
    }

    public static int merge(int[] arr, int L, int R, int M) {
        int[] help = new int[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int i = 0;
        int res = 0;
        while (p1 <= M && p2 <= R) {
            res += arr[p1] < arr[p2] ? arr[p1] * (R - p2 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (int k : help) {
            arr[L++] = k;
        }
        return res;
    }

    public static int compare(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                res += arr[i] > arr[j] ? arr[j] : 0;
            }
        }
        return res;
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue, false);
            int[] copyArr = Tools.copyArr(arr);
            int sum = smallSum(arr);
            int compare = compare(copyArr);
            if (sum != compare) {
                succeed = false;
                Tools.printArray(copyArr);
                System.out.println("sum : " + sum);
                System.out.println("compare : " + compare);
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
//        int[] arr = {0, 2, 0, 4, 4};
//        int i = smallSum(arr);
//        Tools.printArray(arr);
//        System.out.println(i);
    }

}

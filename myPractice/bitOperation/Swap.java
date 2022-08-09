package myPractice.bitOperation;

import tool.Tools;

// 不用中间变量交换两个数
public class Swap {
    public static void swap(int[] arr, int i, int j) {
        if (arr == null || arr.length == 0 || arr[i] == arr[j]) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[j] ^ arr[i];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void normalSwap(int[] arr,int i,int j){
        if (arr == null || arr.length == 0 || arr[i] == arr[j]){
            return ;
        }
        int tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = Tools.generateRandomArray(maxSize, maxValue, true);
            int[] arr2 = Tools.copyArr(arr1);
            int index1 = Tools.getRandomInt(arr1.length-1, false);
            int index2 = Tools.getRandomInt(arr1.length-1, false);
            swap(arr1,index1,index2);
            normalSwap(arr2,index1,index2);
            succeed = Tools.isEqual(arr1, arr2);
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }
}

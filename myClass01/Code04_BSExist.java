package myClass01;

import java.util.Arrays;

public class Code04_BSExist {
    public static boolean binarySearch(int[] arr, int i) {
        if ((arr == null) || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if(arr[mid] == i){
                return true;
            }else if(arr[mid] > i){
                R = mid -1;
            }else{
                L = mid + 1;
            }
        }

        return arr[L] == i;
    }
}
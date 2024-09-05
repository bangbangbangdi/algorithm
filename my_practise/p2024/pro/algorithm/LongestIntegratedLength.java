package my_practise.p2024.pro.algorithm;

import tool.Executable;
import tool.Tools;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * projectName:    algorithm
 * package:        my_practise.p2024.pro.algorithm
 * className:      LongestIntegratedLength
 * author:     BangDi
 * description:  TODO
 * date:    2024/7/14 17:31
 * version:    1.0
 */
public class LongestIntegratedLength {

    public static int longest(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = 1;
        int N = arr.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.clear();
            int len = 1;
            double max = arr[i];
            double min = arr[i];
            double sum = arr[i];
            set.add(arr[i]);
            for (int j = i + 1; j < N; j++) {
                if (set.contains(arr[j])) {
                    break;
                }
                set.add(arr[j]);
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                sum += arr[j];
                len++;
                double sum2 = (max + min) / 2 * len;
                res = (len - 1 == max - min) && (sum == sum2) ? Math.max(res, len) : res;
            }
        }
        return res;
    }

    public static int compare(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int res = 1;
        Queue<Integer> heap = new PriorityQueue<>();
        for (int start = 0; start < N; start++) {
            for (int end = start + 1; end < N; end++) {
                heap.clear();
                for (int i = start; i <= end; i++) {
                    heap.add(arr[i]);
                }
                int len = end - start + 1;
                boolean flg = true;
                int cur = heap.poll();
                while (!heap.isEmpty() && flg) {
                    if (heap.poll() != ++cur) {
                        flg = false;
                    }
                }
                res = flg ? Math.max(res, len) : res;
            }
        }
        return res;
    }

    public static void main(String[] args) {

        Tools.execute(new Executable() {
            @Override
            public boolean execute() {
                int[] arr = Tools.generateRandomArray(20, 10, false);
//                int[] arr = Tools.generateArr();
                int res = longest(arr);
                int compare = compare(arr);
                if (res != compare) {
                    Tools.printArray(arr);
                    System.out.println("res = " + res);
                    System.out.println("compare = " + compare);
                    return false;
                }
                return true;
            }
        });


//        int[] arr = {3, 4, 8, 6, 4, 8, 0, 10, 8, 5};
//        int res = longest(arr);
//        int compare = compare(arr);
//        Tools.printArray(arr);
//        System.out.println("res = " + res);
//        System.out.println("compare = " + compare);
    }

}

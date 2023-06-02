package my_practise.p2023.basic.algorithm.greedy;

import tool.Tools;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.greedy
 * className:      LowestLexicography
 * author:     BangDi
 * description:
 * 给定一个由字符串组成的数组strs，
 * 必须把所有的字符串拼接起来，
 * 返回所有可能的拼接结果中，字典序最小的结果
 * date:    2023/6/2 13:42
 * version:    1.0
 */
public class LowestLexicography {

    public static String ll(String[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        StringBuilder res = new StringBuilder();
        for (String s : arr) {
            res.append(s);
        }
        return res.toString();
    }

    public static String compare(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }
        HashSet<String> set = new HashSet<>();
        StringBuilder path = new StringBuilder();
        process(arr, 0, set, path);
        Iterator<String> iterator = set.iterator();
        String min = iterator.next();
        while (iterator.hasNext()) {
            String next = iterator.next();
            min = min.compareTo(next) < 0 ? min : next;
        }
        return min;
    }

    public static void process(String[] arr, int index, HashSet<String> set, StringBuilder path) {
        if (index == arr.length) {
            set.add(path.toString());
        }
        for (int i = index; i < arr.length; i++) {
            Tools.swap(arr, index, i);
            process(arr, index + 1, set, path.append(arr[index]));
            path.delete(path.length() - arr[index].length(), path.length());
            Tools.swap(arr, index, i);
        }
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 5;
        int strLen = 6;
        int testTimes = 500000;
        boolean succeed = true;
        for (int i = 0; i < testTimes && succeed; i++) {
            String[] arr1 = Tools.generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!ll(arr1).equals(compare(arr2))) {
                for (String s : arr1) {
                    System.out.print(s + "|");
                }
                System.out.println();
                System.out.println("lowestLexicography:" + ll(arr1));
                System.out.println("compare:" + compare(arr2));
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }

}

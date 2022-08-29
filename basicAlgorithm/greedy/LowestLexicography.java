package basicAlgorithm.greedy;
//给定一个由字符串组成的数组strs，
//必须把所有的字符串拼接起来，
//返回所有可能的拼接结果中，字典序最小的结果

import javafx.beans.property.StringProperty;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;

// 这道题首先要将字符串的字典序抽象为数字的排列顺序 也就是将 "abc" --> a*26^2 + b*26^1 + c*26^0
// 此时我们就可以将字符串问题转化为数字问题
// 求字符串字典序最小就等同于求转化后数字最小
public class LowestLexicography {

    public static String lowestLexicography(String[] arr) {
        Arrays.sort(arr, new MyComparator());
        StringBuilder ans = new StringBuilder();
        for (String s : arr) {
            ans.append(s);
        }
        return ans.toString();
    }

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static String compare(String[] arr) {
        if (arr == null || arr.length < 1) {
            return "";
        }
        int index = 0;
        StringBuilder sb = new StringBuilder();
        HashSet<String> set = new HashSet<>();
        process(arr, index, sb, set);
//        set.forEach(System.out::println);
        Iterator<String> iterator = set.iterator();
        String min = iterator.next();
        while (iterator.hasNext()) {
            String next = iterator.next();
            min = min.compareTo(next) < 0 ? min : next;
        }
        return min;
    }

    public static void process(String[] arr, int index, StringBuilder path, HashSet<String> set) {
        if (index == arr.length) {
            set.add(path.toString());
        }
        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            process(arr, index + 1, path.append(arr[index]), set);
            path.delete(path.length() - arr[index].length(),path.length());
            swap(arr, index, i);
        }
    }

    public static void swap(String[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        String tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
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
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestLexicography(arr1).equals(compare(arr2))) {
                for (String s : arr1) {
                    System.out.print(s + "|");
                }
                System.out.println();
                System.out.println("lowestLexicography:" + lowestLexicography(arr1));
                System.out.println("compare:" + compare(arr2));
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }
}

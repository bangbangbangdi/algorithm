package my_practise.p2022.basic.myClass09;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Code01_LowestLexicography {

    public static String lowestLexicography(String[] strs) {
        if (strs == null) {
            return null;
        }
        ArrayList<String> arr = new ArrayList<>();
        process(strs, new HashSet<Integer>(), "", arr);
        String[] arr2 = arr.toArray(new String[]{});
//        for (String str : arr2) {
//            System.out.print(str + " ");
//        }
        Arrays.sort(arr2);
//        System.out.println("=======");
//        for (String str : arr2) {
//            System.out.print(str + " ");
//        }
        return arr2[0];
    }


    // 给定一个字符串数组，请排列出所有的可能结果
    public static void process(String[] strs, HashSet<Integer> usedIndex, String path, ArrayList<String> arr) {
        if (usedIndex.size() == strs.length) {
            arr.add(path);
        } else {
            for (int i = 0; i < strs.length; i++) {
                if (!usedIndex.contains(i)) {
                    usedIndex.add(i);
                    process(strs, usedIndex, path + strs[i], arr);
                    usedIndex.remove(i);
                }
            }
        }
    }

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static String lowestLexicography2(String[] strs) {
        if (strs == null) {
            return null;
        }
        Arrays.sort(strs, new MyComparator());
        String ans = "";
        for (String str : strs) {
            ans += str;
        }
        return ans;
    }


    public static void main(String[] args) {
        String[] strs = new String[]{"b", "ba"};
        String s = lowestLexicography(strs);
        System.out.println(s);
        String s2 = lowestLexicography2(strs);
        System.out.println(s2);
    }

}
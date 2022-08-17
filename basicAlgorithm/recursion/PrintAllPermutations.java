package basicAlgorithm.recursion;

import java.util.*;

public class PrintAllPermutations {

    public static void printAllPermutations(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        char[] chars = str.toCharArray();
        HashSet<String> set = new HashSet<>();
        process(set, chars, 0, "");
        set.forEach(System.out::println);
    }

    public static void process(Set<String> set, char[] chars, int index, String path) {
        if (index == chars.length) {
            set.add(path);
            return;
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            process(set, chars, index + 1, path + chars[index]);
            swap(chars, index, i);
        }
    }


    public static void printAllPermutations2(String str) {
        if (str == null || str.length() < 1) {
            return;
        }
        char[] chars = str.toCharArray();
        ArrayList<String> arr = new ArrayList<>();
        process2(arr,chars,0);
        arr.forEach(System.out::println);
    }

    public static void process2(List<String> arr, char[] chars, int index) {
        if (index == chars.length) {
            arr.add(String.valueOf(chars));
        }
        boolean[] visit = new boolean[26];
        for (int i = index; i < chars.length; i++) {
            if (!visit[chars[i] - 97]) {
                visit[chars[i] - 97] = true;
                swap(chars, index, i);
                process2(arr, chars, index + 1);
                swap(chars, index, i);
            }
        }
    }


    public static void swap(char[] chars, int a, int b) {
        if (a == b) {
            return;
        }
        char tem = chars[a];
        chars[a] = chars[b];
        chars[b] = tem;
    }


    public static void main(String[] args) {
//        printAllPermutations("aab");
//        boolean[] b = new boolean[1];
//        System.out.println(b[0]);
        printAllPermutations2("abc");
    }
}

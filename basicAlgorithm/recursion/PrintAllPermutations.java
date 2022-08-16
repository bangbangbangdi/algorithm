package basicAlgorithm.recursion;

import java.util.HashSet;
import java.util.Set;

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
            process(set, chars, index + 1, path + chars[i]);
        }
    }

    public static void swap(char[] chars, int a, int b) {
        char tem = chars[a];
        chars[a] = chars[b];
        chars[b] = tem;
    }


    public static void main(String[] args) {
        printAllPermutations("abc");
    }
}

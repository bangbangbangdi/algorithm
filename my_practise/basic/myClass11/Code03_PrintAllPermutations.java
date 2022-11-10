package my_practise.basic.myClass11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Code03_PrintAllPermutations {

    public static List<String> permutations(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        char[] chars = str.toCharArray();
        ArrayList<String> ans = new ArrayList<String>();
        process(chars, 0, "", ans);
        return ans;
    }

    // chars 所有的字符
    // 0...index-1 已经决策过了
    // path 已经决策形成的字符串
    // list 结果的集合
    public static void process(char[] chars, int index, String path, List<String> list) {
        if (index == chars.length) {
            list.add(path);
            return;
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            process(chars, index + 1, path + chars[index], list);
            swap(chars, index, i);
        }
    }

    public static List<String> permutationNoRepeat(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        char[] chars = str.toCharArray();
        ArrayList<String> arr = new ArrayList<>();
        process2(chars, 0, "", arr);
        return arr;
    }

    public static void process2(char[] chars, int index, String path, List<String> list) {
        if (index == chars.length) {
            list.add(path);
            return;
        }
        boolean[] visit = new boolean[26];
        for (int i = index; i < chars.length; i++) {
            if (!visit[chars[i] - 'a']) {
                visit[chars[i] - 'a'] = true;
                swap(chars, i, index);
                process2(chars, index + 1, path + chars[index], list);
                swap(chars, i, index);
            }
        }
    }

    public static List<String> permutationNoRepeat2(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        char[] chars = str.toCharArray();
        Set<String> set = new HashSet<>();
        process3(chars, 0, "", set);
        return new ArrayList<>(set);
    }

    public static void process3(char[] chars, int index, String path, Set<String> set) {
        if (index == chars.length) {
            set.add(path);
            return;
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars, i, index);
            process3(chars, index + 1, path + chars[index], set);
            swap(chars, i, index);
        }
    }


    public static void swap(char[] chars, int i, int j) {
        if (chars[i] == chars[j]) {
            return;
        }
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    public static void main(String[] args) {
        String str = "aacc";
        List<String> arr1 = permutations(str);
        List<String> arr2 = permutationNoRepeat(str);
        List<String> arr3 = permutationNoRepeat2(str);
        for (String s : arr1) {
            System.out.println(s);
        }
        System.out.println("-----------------");
        for (String s : arr2) {
            System.out.println(s);
        }
        System.out.println("-----------------");
        for (String s : arr3) {
            System.out.println(s);
        }
    }
}

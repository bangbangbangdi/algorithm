package my_practise.basic.myClass11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Code02_PrintAllSubsquences {

    // aacc
    // a aa aac ac

    public static List<String> subs(String s) {
        if (s == null) {
            return null;
        }
        char[] chars = s.toCharArray();
        ArrayList<String> ans = new ArrayList<>();
        process(chars, 0, "", ans);
        return ans;
    }

    public static void process(char[] chars, int index, String path, List<String> ans) {
        if (index == chars.length) {
            ans.add(path);
            return;
        }
        // 不使用index位置字符情况
        process(chars, index + 1, path, ans);
        // 使用index位置字符情况
        process(chars, index + 1, path + chars[index], ans);
    }

    public static List<String> subsNoRepeat(String s) {
        if (s == null) {
            return null;
        }
        char[] chars = s.toCharArray();
        HashSet<String> set = new HashSet<>();
        process2(chars, 0, "", set);
        return new ArrayList<>(set);
    }

    // chars 可用的字符组
    // 0...index-1已经安排了 现在安排index
    // path 之前已经拼成的字符串
    // ans 已经获取到的结果
    public static void process2(char[] chars, int index, String path, Set<String> ans) {
        if (index == chars.length) {
            ans.add(path);
            return;
        }
        process2(chars, index + 1, path, ans);
        process2(chars, index + 1, path + chars[index], ans);
    }

    public static void main(String[] args) {
        String str = "aacc";
        List<String> arr = subsNoRepeat(str);
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
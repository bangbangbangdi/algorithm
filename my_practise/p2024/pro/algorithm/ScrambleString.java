package my_practise.p2024.pro.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * projectName:    algorithm
 * package:        my_practise.p2024.pro.algorithm
 * className:      ScrambleString
 * author:     BangDi
 * description:  TODO
 * date:    2024/9/5 19:07
 * version:    1.0
 */
public class ScrambleString {

    public static boolean scramble(String str1, String str2) {
        if (!isValid(str1, str2)) {
            return false;
        }
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        return process(s1, s2, 0, s1.length - 1, 0);
    }

    public static boolean process(char[] s1, char[] s2, int l1, int r1, int l2) {
        if (l1 == r1) {
            return s1[l1] == s2[l2];
        }

        int r2 = l2 + r1 - l1;
        for (int i = 0; i < r1 - l1; i++) {
            boolean sub11 = process(s1, s2, l1, l1 + i, l2);
            boolean sub12 = sub11 && process(s1, s2, l1 + i + 1, r1, l2 + i + 1);

            boolean sub21 = process(s1, s2, l1, l1 + i, r2 - i);
            boolean sub22 = sub21 && process(s1, s2, l1 + i + 1, r1, l2);
            if ((sub11 && sub12) || (sub21 && sub22)) {
                return true;
            }
        }

        return false;
    }


    public static boolean isValid(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        HashMap<Character, Integer> wf1 = new HashMap<>();
        HashMap<Character, Integer> wf2 = new HashMap<>();
        for (int i = 0; i < s1.length; i++) {
            char ch1 = s1[i];
            char ch2 = s2[i];
            wf1.put(ch1, wf1.containsKey(ch1) ? wf1.get(ch1) + 1 : 1);
            wf2.put(ch2, wf2.containsKey(ch2) ? wf2.get(ch2) + 1 : 1);
        }
        for (Entry<Character, Integer> entry : wf1.entrySet()) {
            Character ch = entry.getKey();
            Integer frequency = entry.getValue();
            if (!wf2.containsKey(ch) || !wf2.get(ch).equals(frequency)) {
                return false;
            }
        }
        return true;
    }

    public static String getString() {
        return "aa";
    }

    public static void main(String[] args) {
        String str1 = "abcd";
        String str2 = "cadb";
        boolean res = scramble(str1, str2);
        System.out.println("res = " + res);

    }

}

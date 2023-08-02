package my_practise.p2023.pro.algorithm.KMP;

import tool.Tools;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm.KMP
 * className:      RevolveWord
 * author:     BangDi
 * description:
 * 给定两个字符串判断他们是否互为旋转词
 * 旋转词:
 * 1234 -> 1234,2341,3412,4123
 * date:    2023/8/1 22:52
 * version:    1.0
 */
public class RevolveWord {

    public static boolean isRevolve(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        str1 = str1 + str1;
        char[] chs = str1.toCharArray();
        char[] ms = str2.toCharArray();
        int[] next = next(ms);
        int x = 0;
        int y = 0;
        while (x < chs.length && y < ms.length) {
            if (chs[x] == ms[y]) {
                x++;
                y++;
            } else if (next[y] != -1) {
                y = next[y];
            } else {
                x++;
            }
        }
        return y == ms.length;
    }

    public static int[] next(char[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        if (arr.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[arr.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length) {
            if (arr[i - 1] == arr[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static boolean compare(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int x = 0;
        int y = 0;
        for (int i = 0; i < ch1.length; i++) {
            x = i;
            y = 0;
            while (y < ch2.length && ch1[x] == ch2[y]) {
                x = getNextIndex(x, ch1.length - 1);
                y++;
            }
            if (y == ch2.length) {
                return true;
            }
        }
        return false;
    }

    public static int getNextIndex(int index, int max) {
        return index == max ? 0 : ++index;
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 3;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            String s1 = Tools.generateRandomString(maxSize, maxValue);
            String s2 = Tools.generateRandomString(maxSize, maxValue);
            if (isRevolve(s1,s2)){
                System.out.println("s1 = " + s1);
                System.out.println("s2 = " + s2);
            }
            if (isRevolve(s1, s2) != compare(s1, s2)) {
                System.out.println("s1 = " + s1);
                System.out.println("s2 = " + s2);
                System.out.println("isRevolve(s1,s2) = " + isRevolve(s1, s2));
                System.out.println("compare(s1,s2) = " + compare(s1, s2));
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
    }

}

package my_practise.p2024.pro.algorithm;

import tool.Tools;

/**
 * projectName:    algorithm
 * package:        my_practise.p2024.pro.algorithm
 * className:      PalindromeMinEndAdd
 * author:     BangDi
 * description:
 * 给定一个字符串,如果只能在后面添加字符,最少添加几个能让字符串整体都是回文
 * review:
 * 一眼manacher
 * date:    2024/7/17 18:51
 * version:    1.0
 */
public class PalindromeMinEndAdd {

    public static int palindromeMinEndAdd(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int res = s.length();
        char[] str = process(s);
        int[] pArr = new int[str.length];
        int R = 0;
        int C = 0;
        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            if (R >= str.length){
                return (str.length - pArr[i] * 2 + 1)/2;
            }
        }
        return res;
    }

    public static char[] process(String s) {
        char[] str = s.toCharArray();
        char[] res = new char[s.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = i % 2 == 0 ? '#' : str[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "1234565";
//        System.out.println(String.valueOf(process(str)));
        System.out.println("palindromeMinEndAdd(str) = " + palindromeMinEndAdd(str));
    }

}

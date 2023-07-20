package my_practise.p2023.pro.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm
 * className:      ZeroLeftOneStringNumber
 * author:     BangDi
 * description:  TODO
 * 给定一个数N,想象只由0和1两种字符,组成的所有长度为N的字符串
 * 如果某个字符串,任何0字符的左边都有一个1紧挨着,认为这个字符串达标
 * 返回有多少达标的字符串
 * date:    2023/7/9 10:46
 * version:    1.0
 */
public class ZeroLeftOneStringNumber {

    public static int right(int N) {
        if (N < 1) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        process(set, new StringBuilder(), N);
        int res = 0;
        for (String s : set) {
            if (availStr(s)) {
                res++;
            }
        }
        return res;
    }

    public static boolean availStr(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        char[] chs = str.toCharArray();
        if (chs[0] == '0') {
            return false;
        }
        for (int i = 1; i < chs.length; i++) {
            if (chs[i] == '0' && chs[i - 1] != '1') {
                return false;
            }
        }
        return true;
    }

    public static void process(Set<String> set, StringBuilder sb, int length) {
        if (length < 0) {
            return;
        }
        if (length == sb.length()) {
            set.add(sb.toString());
            return;
        }
        process(set, sb.append("1"), length);
        sb.deleteCharAt(sb.length() - 1);
        process(set, sb.append("0"), length);
        sb.deleteCharAt(sb.length() - 1);
    }

    public static int zeroLeftOne(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        if (N == 2) {
            return 2;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, N - 2);
        return 2 * res[0][0] + res[1][0];
    }

    public static int[][] matrixPower(int[][] m, int p) {
        if (m == null || m.length != m[0].length || p < 0) {
            return null;
        }
        int[][] res = new int[m.length][m[0].length];
        int[][] tmp = m;
        for (int i = 0; i < m.length; i++) {
            res[i][i] = 1;
        }
        while (p != 0) {
            if ((p & 1) != 0) {
                res = multiMatrix(res, tmp);
            }
            tmp = multiMatrix(tmp, tmp);
            p >>= 1;
        }
        return res;
    }

    public static int[][] multiMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m1.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(right(15));
        System.out.println(zeroLeftOne(15));
    }

}

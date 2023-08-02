package my_practise.p2023.pro.algorithm.KMP;

import tool.Tools;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm.KMP
 * className:      KMP
 * author:     BangDi
 * description:
 * 1.提供两个字符串match和str,查询str中是否包含match(注意这里说的包含是子串,必须是连续的)
 * 2.如果包含则提供str中match的索引开头,不包含返回-1
 * 例如:str=ab1234cd  match=1234  返回2
 * date:    2023/7/31 19:33
 * version:    1.0
 */
public class KMP {

    public static int getIndexOf(String str, String match) {
        if (str == null || match == null || str.length() < match.length()) {
            return -1;
        }
        if (match.length() == 0) {
            return 0;
        }
        char[] chs = str.toCharArray();
        char[] ms = match.toCharArray();
        int[] next = getNextArray(ms);
        int x = 0;
        int y = 0;
        while (x < chs.length && y < ms.length) {
            if (chs[x] == ms[y]) {
                x++;
                y++;
            } else if (next[y] >= 0) {
                y = next[y];
            } else {
                x++;
            }
        }
        return y == ms.length ? x - y : -1;
    }

    public static int[] getNextArray(char[] match) {
        if (match == null || match.length == 0) {
            return null;
        }
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int i = 2;
        while (i < match.length) {
            if (match[i - 1] == match[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        boolean succeed = true;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes && succeed; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                succeed = false;
                System.out.println("match = " + match);
                System.out.println("str = " + str);
                System.out.println("getIndexOf(str,match) = " + getIndexOf(str, match));
                System.out.println("str.indexOf(match) = " + str.indexOf(match));
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}

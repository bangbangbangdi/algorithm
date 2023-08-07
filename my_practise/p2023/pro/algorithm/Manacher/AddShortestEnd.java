package my_practise.p2023.pro.algorithm.Manacher;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm.Manacher
 * className:      AddShortesEnd
 * author:     BangDi
 * description:咩咩
 * 在字符串后面添加最少的字符使整个字符串为回文
 * 返回需要添加的字符串
 * date:    2023/8/4 20:32
 * version:    1.0
 */
public class AddShortestEnd {

    public static String addShortest(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char[] chs = getChs(str);
        int[] pArr = new int[chs.length];
        int R = -1;
        int C = -1;
        StringBuilder sb = new StringBuilder();
        // 第一个回文摸到数组最后的字符的索引
        int f = 0;
        for (int i = 0; i < chs.length; i++) {
            pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i - pArr[i] > -1 && i + pArr[i] < chs.length) {
                if (chs[i - pArr[i]] == chs[i + pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] < R) {
                R = i + pArr[i];
                C = i;
            }
            if (i + pArr[i] == chs.length ) {
                f = i;
                break;
            }
        }
        for (int i = f - pArr[f]; i > -1; i-=2) {
            sb.append(chs[i]);
        }
        return sb.toString();
    }


    public static char[] getChs(String str) {
        char[] chars = new char[str.length() * 2 + 1];
        char[] arr = str.toCharArray();
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (i & 1) == 0 ? '#' : arr[index++];
        }
        return chars;
    }

    public static void main(String[] args) {
        System.out.println(addShortest("efgabcba"));
    }

}

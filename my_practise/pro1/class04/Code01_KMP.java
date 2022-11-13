package my_practise.pro1.class04;

// 大名鼎鼎的KMP算法
// 假设字符串str长度为N,字符串match长度为M,M<=N
// 想确定str中是否有某个字串是等于match的
// 时间复杂度为O(N)
public class Code01_KMP {

    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || s.length() < m.length()) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int[] next = getNextArray(str2);
        int x = 0;
        int y = 0;
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }


    // 获取next数组
    // 该数组表示, 一个字符串中每一个位置的一个指标,它之前字符串最长前缀和最长后缀的匹配长度
    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        // 这里的cn有两种含义
        // 1.表示当前是哪个索引的字符在跟i-1进行比较
        // 2.表示当前满足条件的字符串长度
        int cn = 0;
        while (i < ms.length) {
            // 如果能成功匹配
            if (ms[cn] == ms[i - 1]) {
                // i++ 来到下一个匹配的位置
                // next[i] = cn + 1 next中对应的最长前缀和最长后缀的匹配长度+1
                // cn = cn + 1  下一次匹配的位置+1
                next[i++] = ++cn;
            } else if (cn > 0) {
                // 如果没有匹配上,且cn>0
                cn = next[cn];
            } else {
                // 如果cn已经来到了0位置还是没有匹配上,那最大匹配长度就是0,i++ 来到下一个需要匹配的位置
                next[i++] = 0;
            }
        }
        return next;
    }

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
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}

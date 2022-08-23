package basicAlgorithm.dynamicProgramming.leftToRight;

// 给定一个字符串str 给定一个字符串类型数组 arr 只包含小写字母
// arr里的每一个字符串,代表一张贴纸,你可以把单子字符剪开使用,目的是拼出str来
// 每张贴纸可以用无穷多次
// 返回至少需要多少张贴纸可以完成这个任务
// 栗子 str = "babac" arr = {"ba","c","abcd"}
// 至少需要两张贴纸 "ba" "abcd" 所以返回2

import java.util.HashMap;

public class StickersToSpellWord {

    public static int stickers(String str, String[] arr) {
        if (str == null || arr == null || str.length() < 1 || arr.length < 1) {
            return -1;
        }
        int N = arr.length;
        HashMap<String, Integer> dp = new HashMap<>();
        int[][] bulletWF = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] chars = arr[i].toCharArray();
            for (char ch : chars) {
                bulletWF[i][ch - 'a']++;
            }
        }
        dp.put("", 0);
        return process(dp, bulletWF, str);
    }

    public static int process(HashMap<String, Integer> dp, int[][] bulletWF, String str) {
        if (dp.containsKey(str)) {
            return dp.get(str);
        }
        int ans = Integer.MAX_VALUE;
        char[] target = str.toCharArray();
        int[] targetWF = new int[26];
        for (char ch : target) {
            targetWF[ch - 'a']++;
        }

        for (int i = 0; i < bulletWF.length; i++) {
            if (bulletWF[i][target[0] - 'a'] == 0) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < targetWF.length; j++) {
                if (targetWF[j] != 0) {
                    for (int k = 0; k < Math.max(0, targetWF[j] - bulletWF[i][j]); k++) {
                        sb.append((char) ('a' + j));
                    }
                }
            }
            String sub = sb.toString();
            int tem = process(dp, bulletWF, sub);
            if (tem != -1) {
                ans = Math.min(ans, tem + 1);
            }
        }
        dp.put(str, ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(str);
    }

    public static void main(String[] args) {
        String str = "aabbcccccddddeeee";
        String[] arr = {"abb","ccdd","ddee"};
        System.out.println(stickers(str, arr));
    }

}

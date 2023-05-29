package my_practise.p2023.basic.algorithm.dynamicProgramming.leftToRight;

import java.util.HashMap;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.algorithm.dynamicProgramming.leftToRight
 * className:      StickersToSpellWord
 * author:     BangDi
 * description:
 * // 给定一个字符串str 给定一个字符串类型数组 arr 只包含小写字母
 * // arr里的每一个字符串,代表一张贴纸,你可以把单子字符剪开使用,目的是拼出str来
 * // 每张贴纸可以用无穷多次
 * // 返回至少需要多少张贴纸可以完成这个任务
 * // 栗子 str = "babac" arr = {"ba","c","abcd"}
 * // 至少需要两张贴纸 "ba" "abcd" 所以返回2
 * date:    2023/5/28 23:03
 * version:    1.0
 */
public class StickersToSpellWord {

    public static int stickersToSpellWord(String str, String[] arr) {
        if (str == null || str.length() < 1 || arr == null || arr.length < 1) {
            return -1;
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int[][] bulletWF = new int[arr.length][26];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length(); j++) {
                bulletWF[i][arr[i].charAt(j) - 97]++;
            }
        }
        return process(dp, bulletWF, str);
    }

    public static int process(HashMap<String, Integer> dp, int[][] bulletWF, String str) {
        if (dp.get(str) != null) {
            return dp.get(str);
        }
        char[] target = str.toCharArray();
        int[] targetWF = new int[26];
        int res = Integer.MAX_VALUE;
        for (char c : target) {
            targetWF[c - 97]++;
        }
        for (int i = 0; i < bulletWF.length; i++) {
            if (bulletWF[i][target[0] - 97] == 0) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                while (targetWF[j] - bulletWF[i][j] > 0) {
                    sb.append((char) (97 + j));
                    targetWF[j]--;
                }
            }
            int tmp = process(dp, bulletWF, sb.toString());
            if (tmp != -1) {
                res = Math.min(res, tmp + 1);
            }
        }
        dp.put(str, res == Integer.MAX_VALUE ? -1 : res);
        return dp.get(str);
    }


    public static void main(String[] args) {
        String str = "aabbcccccddddeeee";
        String[] arr = {"abb", "ccdd", "ddee"};
        System.out.println(stickersToSpellWord(str, arr));

    }
}

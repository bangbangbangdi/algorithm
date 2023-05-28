package my_practise.p2023.basic.algorithm.dynamicProgramming.leftToRight;

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
            return 0;
        }
//        int[] target = new int[26];
//        int[][] bullet = new int[arr.length][26];
//        for (int i = 0; i < str.length(); i++) {
//            target[str.charAt(i) - 48]++;
//        }
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length(); j++) {
//                bullet[i][arr[i].charAt(j) - 48]++;
//            }
//        }
        return process(str, arr, 0, 0);
    }

    public static int process(String str, String[] arr, int index, int count) {
        if (str.equals("")) {
            return count;
        }
        int[] target = new int[26];
        int[] bullet = new int[26];
        String b = arr[index];
        for (int i = 0; i < str.length(); i++) {
            target[str.charAt(i) - 48]++;
        }
        for (int i = 0; i < b.length(); i++) {
            bullet[b.charAt(i) - 48]++;
        }
        for (int i = 0; i < 26; i++) {

        }
        return 1;
    }

}

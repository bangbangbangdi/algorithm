package my_practise.basic.myClass12;

import tool.Tools;

import java.util.HashMap;

public class Code02_StickersToSpellWord {

    public static int stickerToSpellWord(String str, String[] arr) {
        if (str == null || arr == null || str.length() == 0 || arr.length == 0) {
            return 0;
        }
        int[] rest = new int[26];
        int[][] strArr = new int[str.length()][26];
        char[] chars = str.toCharArray();

        for (char c : chars) {
            rest[c - 'a']++;
        }

        for (int i = 0; i < arr.length; i++) {
            char[] charArray = arr[i].toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                strArr[i][charArray[j] - 'a']++;
            }
        }
        return process(strArr, rest, 0);
    }

    public static int process(int[][] arr, int[] rest, int count) {
        if (Tools.sum(rest) == 0) {
            return count;
        }
        int ontZero = -1;
        for (int i = 0; i < rest.length; i++) {
            if (rest[i] != 0) {
                ontZero = i;
                break;
            }
        }
        int ans = Integer.MAX_VALUE;
        int[] tem = null;
        for (int[] ints : arr) {
            if (ints[ontZero] == 0) {
                continue;
            }
            tem = Tools.copyArr(rest);
            // 撞掉目标字符
            for (int j = 0; j < ints.length; j++) {
                tem[j] = Math.max(0, rest[j] - ints[j]);
            }
            ans = Math.min(ans, process(arr, tem, count + 1));
        }
        return ans;
    }

    public static int stickerToSpellWord2(String str, String[] arr) {
        if (str == null || arr == null || str.length() == 0 || arr.length == 0) {
            return 0;
        }
        int[] rest = new int[26];
        int[][] strArr = new int[str.length()][26];
        char[] chars = str.toCharArray();

        for (char c : chars) {
            rest[c - 'a']++;
        }

        for (int i = 0; i < arr.length; i++) {
            char[] charArray = arr[i].toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                strArr[i][charArray[j] - 'a']++;
            }
        }
        return process2(strArr, rest);
    }

    // 删去了一个参数,不过由于可变参数是int[] 所以没办法改出动态规划的版本
    public static int process2(int[][] arr, int[] rest) {
        if (Tools.sum(rest) == 0) {
            return 0;
        }
        int ontZero = -1;
        for (int i = 0; i < rest.length; i++) {
            if (rest[i] != 0) {
                ontZero = i;
                break;
            }
        }
        int ans = Integer.MAX_VALUE;
        int[] tem = null;
        for (int[] ints : arr) {
            if (ints[ontZero] == 0) {
                continue;
            }
            tem = Tools.copyArr(rest);
            // 撞掉目标字符
            for (int j = 0; j < ints.length; j++) {
                tem[j] = Math.max(0, rest[j] - ints[j]);
            }
            int p1 = process2(arr, tem);
            ans = Math.min(ans, p1 + 1);
        }
        return ans;
    }

    public static int stickerToSpellWord3(String str, String[] arr) {
        if (str == null || arr == null || str.length() == 0 || arr.length == 0) {
            return 0;
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int[][] strArr = new int[arr.length][26];

        for (int i = 0; i < arr.length; i++) {
            char[] chars = arr[i].toCharArray();
            for (char aChar : chars) {
                strArr[i][aChar - 'a']++;
            }
        }

        return process3(dp, strArr, str);
    }

    public static int process3(HashMap<String, Integer> dp, int[][] strArr, String target) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        char[] t = target.toCharArray();
        int[] tArr = new int[26];
        for (char c : t) {
            tArr[c - 'a']++;
        }
        int ans = Integer.MAX_VALUE;
        // 每个字符串都可以试一遍
        for (int[] ints : strArr) {
            if (ints[t[0] - 'a'] == 0) {
                continue;
            }
            // 撞掉重复的字符
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < ints.length; j++) {
                if (tArr[j] > 0) {
                    for (int k = 0; k < Math.max(0, tArr[j] - ints[j]); k++) {
                        sb.append((char) (j + 'a'));
                    }
                }
            }
            int p1 = process3(dp, strArr, sb.toString());
            ans = Math.min(ans, p1 + 1);
        }
        dp.put(target, ans);
        return dp.get(target);
    }

    public static void main(String[] args) {
        String str = "aabbcccccddddeeee";
        String[] strArr = {"abb", "ccdd", "ddee"};
        System.out.println(stickerToSpellWord(str, strArr));
        System.out.println(stickerToSpellWord2(str, strArr));
        System.out.println(stickerToSpellWord3(str, strArr));
    }

}
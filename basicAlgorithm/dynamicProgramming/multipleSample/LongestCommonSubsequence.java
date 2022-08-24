package basicAlgorithm.dynamicProgramming.multipleSample;

// 返回两个字符串的最长公共子序列的长度
public class LongestCommonSubsequence {

    public static int lcs(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < 1 || str2.length() < 1) {
            return 0;
        }
        int N1 = str1.length();
        int N2 = str2.length();
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        return process(chs1, chs2, N1 - 1, N2 - 1);
    }

    public static int process(char[] chs1, char[] chs2, int i, int j) {
        if (i == 0 && j == 0) {
            return chs1[0] == chs2[0] ? 1 : 0;
        }
        if (i == 0) {
            return Math.max(chs1[0] == chs2[j] ? 1 : 0, process(chs1, chs2, i, j - 1));
        }
        if (j == 0) {
            return process(chs1, chs2, i - 1, j);
        }
        return 1;
    }

    public static int lcs2(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < 1 || str2.length() < 1) {
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        return process2(chs1, chs2);
    }

    public static int process2(char[] chs1, char[] chs2) {
        int N1 = chs1.length;
        int N2 = chs2.length;
        int[][] dp = new int[N1][N2];
        dp[0][0] = chs1[0] == chs2[0] ? 1 : 0;
        for (int i = 1; i < N1; i++) {
            dp[i][0] = dp[i - 1][0] == 1 ? 1 : (chs1[i] == chs2[0] ? 1 : 0);
        }
        for (int j = 1; j < N2; j++) {
            dp[0][j] = dp[0][j - 1] == 1 ? 1 : (chs2[j] == chs1[0] ? 1 : 0);
        }
        for (int i = 1; i < N1; i++) {
            for (int j = 1; j < N2; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (chs1[i] == chs2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[N1 - 1][N2 - 1];
    }

    public static void main(String[] args) {
        String str1 = "abcd";
        String str2 = "aabbeagasderqwec";
        System.out.println(lcs2(str1, str2));
    }
}

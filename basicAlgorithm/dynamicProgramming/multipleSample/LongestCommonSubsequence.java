package basicAlgorithm.dynamicProgramming.multipleSample;

// 返回两个字符串的最长公共子序列的长度
public class LongestCommonSubsequence {

    public static int lcs(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < 1 || str2.length() < 1) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        char[] cha1 = str1.toCharArray();
        char[] cha2 = str2.toCharArray();
        return process(cha1, cha2, cha1.length - 1, cha2.length - 1);
    }

    public static int process(char[] cha1, char[] cha2, int i, int j) {
        if (i == 0 && j == 0) {
            return cha1[0] == cha2[0] ? 1 : 0;
        }
        if (i == 0) {
            return process(cha1, cha2, i, j - 1);
        }
        if (j == 0) {
            return process(cha1, cha2, i - 1, j);
        }
        int ans = Math.max(process(cha1, cha2, i, j - 1), process(cha1, cha2, i - 1, j));
        if (cha1[i] == cha2[j]) {
            ans = process(cha1, cha2, i - 1, j - 1) + 1;
        }
        return ans;
    }

    public static int lcs2(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < 1 || str2.length() < 1) {
            return 0;
        }
        char[] cha1 = str1.toCharArray();
        char[] cha2 = str2.toCharArray();
        return process2(cha1, cha2);
    }

    public static int process2(char[] cha1, char[] cha2) {
        int N1 = cha1.length;
        int N2 = cha2.length;
        int[][] dp = new int[N1][N2];
        dp[0][0] = cha1[0] == cha2[0] ? 1 : 0;
        for (int i = 1; i < N1; i++) {
            dp[i][0] = dp[i - 1][0] == 1 ? 1 : (cha1[i] == cha2[0] ? 1 : 0);
        }
        for (int i = 1; i < N2; i++) {
            dp[0][i] = dp[0][i - 1] == 1 ? 1 : (cha2[i] == cha1[0] ? 1 : 0);
        }
        for (int i = 1; i < N1; i++) {
            for (int j = 1; j < N2; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                if (cha1[i] == cha2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[N1 - 1][N2 - 1];
    }

    public static void main(String[] args) {
        String str1 = "abcdefgqwezzxceah";
        String str2 = "aabbdettxxaafzxcwe";
        System.out.println(lcs(str1, str2));
        System.out.println(lcs2(str1, str2));
    }
}

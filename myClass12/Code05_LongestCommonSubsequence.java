package myClass12;

public class Code05_LongestCommonSubsequence {

    public static int lcse(char[] ch1, char[] ch2) {
        int len1 = ch1.length;
        int len2 = ch2.length;
        int[][] dp = new int[len1][len2];
        // 初始化操作
        dp[0][0] = ch1[0] == ch2[0] ? 1 : 0;
        for (int i = 1; i < len1; i++) {
            dp[i][0] = Math.max(dp[0][i - 1], ch2[0] == ch1[i] ? 1 : 0);
        }
        for (int i = 1; i < len2; i++) {
            dp[0][i] = Math.max(dp[i - 1][0], ch1[0] == ch2[i] ? 1 : 0);
        }

        for (int j = 1; j < len2; j++) {
            for (int i = 1; i < len1; i++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                if (ch1[i] == ch2[j]) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i][j]);
                }
            }
        }

        return dp[len1 - 1][len2 - 1];
    }

    public static void main(String[] args) {
        String str1 = "a1b2c3d4";
        String str2 = "e1f2g3h";
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        System.out.println(lcse(ch1, ch2));
    }

}

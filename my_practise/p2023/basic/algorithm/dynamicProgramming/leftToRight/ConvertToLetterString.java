package my_practise.p2023.basic.algorithm.dynamicProgramming.leftToRight;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.algorithm.dynamicProgramming.leftToRight
 * className:      ConvertToLetterString
 * author:     BangDi
 * description:
 * // 规定1和A对应,2和B对应,3和C对应...
 * // 那么一个数字字符串111就可以转化为
 * // AAA  KA AK
 * // 给定一个只有数字字符组成的字符串str,返回有多少种转化结果
 * date:    2023/5/28 13:35
 * version:    1.0
 */
public class ConvertToLetterString {

    public static int convertToLetterString(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        int[] arr = new int[str.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = str.charAt(i) - 48;
        }
        return process(arr, 0);
    }

    public static int process(int[] arr, int index) {
        if (index == arr.length) {
            return 1;
        }
        if (index > arr.length || arr[index] == 0) {
            return 0;
        }
        if (arr[index] == 1 || (arr[index] == 2 && index + 1 < arr.length && arr[index + 1] <= 6)) {
            return process(arr, index + 1) + process(arr, index + 2);
        } else {
            return process(arr, index + 1);
        }
    }

    public static int convertToLetterStringDp(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        int N = str.length();
        int[] arr = new int[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = str.charAt(i) - 48;
        }
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (i + 2 <= N && (arr[i] == 1 || (arr[i] == 2 && i + 1 < N && arr[i + 1] <= 6))) {
                dp[i] = dp[i + 1] + dp[i + 2];
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }


    public static void main(String[] args) {
        System.out.println(convertToLetterString("122111"));
        System.out.println(convertToLetterStringDp("122111"));
    }

}

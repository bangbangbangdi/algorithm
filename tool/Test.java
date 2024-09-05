package tool;


public class Test {

    public static int compare(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] dp = new int[arr.length];
        int res = 1;
        dp[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            int pre = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j] && dp[j] > pre) {
                    pre = dp[j];
                }
            }
            dp[i] = pre + 1;
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static int lis(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] top = new int[N];
        int plies = 0;
        for (int poker : arr) {
            int left = 0, right = plies;
            while (left < right) {
                int mid = left + ((right - left) >> 1);
                if (top[mid] < poker) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            plies += left == plies ? 1 : 0;
            top[left] = poker;
        }
        return plies;
    }

    public static void main(String[] args) {
        boolean succeed = true;
        for (int i = 0; i < 100000 && succeed; i++) {
            int[] arr = Tools.generateRandomArray(100, 100);
            int lis = lis(arr);
            int compare = compare(arr);
            if (lis != compare) {
                Tools.printArray(arr);
                System.out.println("lis = " + lis);
                System.out.println("compare = " + compare);
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "F");

    }

}

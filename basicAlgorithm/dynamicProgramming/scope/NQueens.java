package basicAlgorithm.dynamicProgramming.scope;

public class NQueens {

    public static int getNQueen(int N) {
        if (N < 1) {
            return -1;
        }
        int[] arr = new int[N];
        return process(arr, 0);
    }

    public static int process(int[] arr, int index) {
        if (index == arr.length) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[index] = i;
            if (isAvailable(arr, index)) {
                res += process(arr, index + 1);
            }
        }
        return res;
    }

    // 只需看到i
    public static boolean isAvailable(int[] arr, int index) {
        for (int i = 0; i < index; i++) {
            // 列冲突 arr[i] == arr[index]
            // 斜线冲突 |i-index|/|arr[i]-arr[index]| == 1
            // 下述写法错在如果遇到4/3这种它也成立
            // if (arr[index] == arr[i] || Math.abs(i - index) / Math.abs(arr[i] - arr[index]) == 1) {
            if (arr[index] == arr[i] || Math.abs(i - index)  == Math.abs(arr[i] - arr[index])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(getNQueen(5));
    }

}

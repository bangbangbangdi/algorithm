package my_practise.basic.myClass11;

public class Code09_NQueens {

    public static int num1(int N) {
        if (N < 1) {
            return 0;
        }
        return process(new int[N], 0);
    }

    // record[0] ... record[i-1] 表示之前已经决策过的,不再关心
    // i 表示第i行棋子
    // j 表示第i行棋子考虑摆放的列数
    public static int process(int[] record, int i) {
        if (i == record.length) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < record.length; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process(record, i + 1);
            }
        }
        return res;
    }

    // 我需要考虑的是 当前的棋子 （i,j）是否与record中[0...i-1]会有冲突
    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (record[k] == j || Math.abs(i - k) == Math.abs(j - record[k])) {
                return false;
            }
        }
        return true;
    }

    public static int num2(int N) {
        if (N > 32 || N < 0) {
            return 0;
        }
        int limit = N == 32 ? -1 : (1 << N) - 1;
        return process2(limit, 0, 0, 0);
    }

    public static int process2(int limit, int colLim, int leftLim, int rightLim) {
        if (limit == colLim) {
            return 1;
        }
        int pos = limit & (~(colLim | leftLim | rightLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos -= mostRightOne;
            res += process2(limit,
                    colLim | mostRightOne,
                    (leftLim | mostRightOne) << 1,
                    (rightLim + mostRightOne) >>> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int N = 15;
        long num1Start = System.currentTimeMillis();
        int res1 = num1(N);
        long num1End = System.currentTimeMillis();
        System.out.println("num1: "+ res1);
        System.out.println(num1End-num1Start + " millis");

        System.out.println("==================");

        long num2Start = System.currentTimeMillis();
        int res2 = num2(N);
        long num2End = System.currentTimeMillis();
        System.out.println("num2: "+ res2);
        System.out.println(num2End-num2Start + " millis");
    }

}

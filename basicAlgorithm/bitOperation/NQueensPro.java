package basicAlgorithm.bitOperation;

public class NQueensPro {

    public static int getNQueens(int N) {
        if (N < 1) {
            return -1;
        }
        int limit = N == 32 ? -1 : (1 << N) - 1;
        return process(limit, 0, 0, 0);
    }

    // 递归的时候要注意不要污染了环境
    public static int process(int limit, int col, int left, int right) {
        if (col == limit) {
            return 1;
        }
        int pos = ~(col | left | right) & limit;
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos -= mostRightOne;
            res += process(limit,
                    col | mostRightOne,
                    (left | mostRightOne) << 1,
                    (right | mostRightOne) >>> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getNQueens(10));
    }

}

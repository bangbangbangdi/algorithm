package my_practise.basic.myClass11;

public class Code08_CardsInLine {

    //    给定一个整型数组arr，代表数值不同的纸牌排成一条线，
    //    玩家A和玩家B依次拿走每张纸牌，
    //    规定玩家A先拿，玩家B后拿，
    //    但是每个玩家每次只能拿走最左或最右的纸牌，
    //    玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    public static int f(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
    }

    public static int s(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
    }


    public static int dpWay(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        int[][] dpF = new int[N][N];
        int[][] dpS = new int[N][N];

        // 初始化操作
        for (int i = 0; i < N; i++) {
            dpF[i][i] = arr[i];
        }

        for (int i = 1; i < N; i++) {
            int L = 0;
            int R = i;
            while(R<N){
                dpF[L][R] = Math.max(arr[L]+dpS[L+1][R],arr[R]+dpS[L][R-1]);
                dpS[L][R] = Math.min(dpF[L+1][R],dpF[L][R-1]);
//                System.out.println("L " + L+" R "+R);
                R++;
                L++;
            }
        }


        return Math.max(dpF[0][N - 1], dpS[0][N - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {6, 10, 7, 1};
        System.out.println(win1(arr));
        System.out.println(dpWay(arr));
    }

}

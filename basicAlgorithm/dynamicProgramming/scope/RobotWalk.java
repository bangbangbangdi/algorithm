package basicAlgorithm.dynamicProgramming.scope;

// 假设有排成一行的N个位置,记为1~N,N一定大于或等于2
// 开始时机器人在其中的M位置上(M一定是1~N中的一个)
// 如果机器人来到1位置,那么下一步只能往右来到2位置
// 如果机器人来到N位置,那么下一步只能往左来到N-1位置
// 如果机器人来到中间位置,那么下一步可以往左或者往右
// 规定机器人必须走K步,最终能来到P位置(P也是1~N中的一个)的方法有多少种
// 给定四个参数N,M,K,P 返回方法数
public class RobotWalk {

    public static int robotWalk(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 0 || P < 1 || P > N) {
            return -1;
        }
        return process(N, M, K, P);
    }

    public static int process(int N, int M, int K, int P) {
        if (K == 0) {
            if (M == P) {
                return 1;
            }
            return 0;
        }
        if (M == 1) {
            return process(N, M + 1, K - 1, P);
        }
        if (M == N) {
            return process(N, M - 1, K - 1, P);
        }
        return process(N, M + 1, K - 1, P) + process(N, M - 1, K - 1, P);
    }

    public static int robotWalk2(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || K < 0 || P < 1 || P > N) {
            return -1;
        }

        return 1;
    }



    public static void main(String[] args) {
        int N = 4;
        int M = 3;
        int K = 2;
        int P = 2;
        System.out.println(robotWalk(N, M, K, P));
    }
}

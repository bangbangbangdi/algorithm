package basicAlgorithm.dynamicProgramming.scope;

public class NQueens {

    public static int getNQueen(int N){
        if (N < 1){
            return -1;
        }
        int[] arr = new int[N];
        return process(arr,0,0);
    }

    public static int process(int[] arr,int index,int j){
        if (index == arr.length){
            return 1;
        }
        return 1;
    }

}

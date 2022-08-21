package basicAlgorithm.dynamicProgramming.scope;

public class NQueens {
    public static int getNQueens(int N){
        if (N < 1){
            return -1;
        }
        int[] record = new int[N];
        return process(record, 0);
    }

    public static int process(int[] record,int index){
        if (index == record.length){
            return 1;
        }
        int res = 0;
        for (int i = 0; i < record.length; i++) {
            record[index] = i;
            if (isValid(record,index)){
                res += process(record,index+1);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record,int index){
        for (int i = 0; i < index; i++) {
            if (record[i] == record[index] || Math.abs(index - i) == Math.abs(record[index] - record[i])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int N = 5;
        System.out.println(getNQueens(N));
    }
}

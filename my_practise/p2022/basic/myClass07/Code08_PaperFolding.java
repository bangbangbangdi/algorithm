package my_practise.p2022.basic.myClass07;

public class Code08_PaperFolding {
    public static void printAllFolds(int N) {
        printProcess(N, "down");
    }

    public static void printProcess(int N, String str) {
        if (N < 1) {
            return ;
        }
        printProcess(N - 1, "down");
        System.out.println(str);
        printProcess(N - 1, "up");
    }

    public static void main(String[] args) {
        printAllFolds(3);
    }
}

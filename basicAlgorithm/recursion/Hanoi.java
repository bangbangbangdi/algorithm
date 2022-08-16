package basicAlgorithm.recursion;

public class Hanoi {
    public static void hanoi(int N) {
        process(N, "L", "R", "M");
    }

    public static void process(int N, String from, String to, String other) {
        if (N == 1) {
            System.out.println(from + "-->" + to);
            return;
        }
        process(N - 1, from, other, to);
        System.out.println(from + "-->" + to);
        process(N - 1, other, to, from);
    }

    public static void main(String[] args) {
        hanoi(3);
    }
}

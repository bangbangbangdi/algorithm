package myClass11;

public class Code01_Hanoi {
    public static void hanoi(int n) {
        func(n, "left", "right", "mid");
    }

    public static void func(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println(from + " move to " + to);
            return;
        }
        func(n - 1, from, other, to);
        System.out.println(from + " move to " + to);
        func(n - 1, other, to, from);
    }

    public static void main(String[] args) {
        hanoi(3);
    }
}
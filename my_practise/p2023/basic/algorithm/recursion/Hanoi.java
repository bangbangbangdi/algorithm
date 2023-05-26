package my_practise.p2023.basic.algorithm.recursion;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.recursion
 * className:      Hanoi
 * author:     BangDi
 * description:  汉诺塔:打印汉诺塔的移动轨迹
 * date:    2023/5/26 10:34
 * version:    1.0
 */
public class Hanoi {

    public static void hanoi(int N) {
        process(N,"from","to","other");
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

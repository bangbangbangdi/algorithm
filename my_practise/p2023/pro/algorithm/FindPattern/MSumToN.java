package my_practise.p2023.pro.algorithm.FindPattern;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm.FindPattern
 * className:      MSumToN
 * author:     BangDi
 * description:
 * 定义一种数：可以表示成若干（数量>1）连续正数和的数
 * 比如:
 * 5 = 2+3，5就是这样的数
 * 12 = 3+4+5，12就是这样的数
 * 1不是这样的数，因为要求数量大于1个、连续正数和
 * 2 = 1 + 1，2也不是，因为等号右边不是连续正数
 * 给定一个参数N，返回是不是可以表示成若干连续正数和的数
 * date:    2023/8/17 22:11
 * version:    1.0
 */
public class MSumToN {

    public static boolean isMSum1(int num) {
        int start = 1;
        while (start < num) {
            int sum = start;
            int plus = start;
            while (sum <= num) {
                if (sum == num) {
                    return true;
                }
                sum += ++plus;
            }
            start++;
        }
        return false;
    }

    public static boolean isMSum2(int num) {
        return (num & (num - 1)) != 0;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 10000; i++) {
            boolean r1 = isMSum1(i);
            boolean r2 = isMSum2(i);
            if (r1 != r2){
                System.out.println("i = " + i);
                System.out.println("r1 = " + r1);
                System.out.println("r2 = " + r2);
                System.out.println("F");
                return ;
            }
        }
        System.out.println("Nice");
    }

}
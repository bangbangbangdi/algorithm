package my_practise.p2023.pro.algorithm.FindPattern;

import java.util.HashMap;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm.FindPattern
 * className:      AppleMinBags
 * author:     BangDi
 * description:
 * 小虎去买苹果，商店只提供两种类型的塑料袋，每种类型都有任意数量。
 * 1）能装下6个苹果的袋子
 * 2）能装下8个苹果的袋子
 * 小虎可以自由使用两种袋子来装苹果，但是小虎有强迫症，他要求自己使用的袋子数量必须最少，且使用的每个袋子必须装满。
 * 给定一个正整数N，返回至少使用多少袋子。如果N无法让使用的每个袋子必须装满，返回-1
 * date:    2023/8/16 22:24
 * version:    1.0
 */
public class AppleMinBags {

    public static int compare(int N) {
        if ((N & 1) != 0) {
            return -1;
        }
        HashMap<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0);
        int res = process(N, dp);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public static int process(int N, HashMap<Integer, Integer> dp) {
        if (dp.containsKey(N)) {
            return dp.get(N);
        }
        if (N < 6 || N == 7) {
            return Integer.MAX_VALUE;
        }
        int res = Math.min(process(N - 6, dp), process(N - 8, dp));
        res = res == Integer.MAX_VALUE ? Integer.MAX_VALUE : res + 1;
        dp.put(N, res);
        return res;
    }

    public static int pattern(int N) {
        if ((N & 1) != 0) {
            return -1;
        }
        if (N < 11 && N != 6 && N != 8 && N != 0) {
            return -1;
        }
        return N % 8 == 0 ? N / 8 : N / 8 + 1;
    }


    public static int minBagAwesome(int apple) {
        if ((apple & 1) != 0) { // 如果是奇数，返回-1
            return -1;
        }
        if (apple < 18) {
            return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1
                    : (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
        }
        return (apple - 18) / 8 + 3;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            int res1 = minBagAwesome(i);
            int res2 = pattern(i);
            if (res1 != res2) {
                System.out.println("i = " + i);
                System.out.println("res1 = " + res1);
                System.out.println("res2 = " + res2);
                System.out.println("F");
                return;
            }
        }
        System.out.println("Nice");
    }

}

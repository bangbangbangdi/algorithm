package my_practise.p2023.pro.algorithm.FindPattern;

import java.util.HashMap;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm.FindPattern
 * className:      EatGrass
 * author:     BangDi
 * description:
 * 给定一个正整数N，表示有N份青草统一堆放在仓库里
 * 有一只牛和一只羊，牛先吃，羊后吃，它俩轮流吃草
 * 不管是牛还是羊，每一轮能吃的草量必须是：
 * 1，4，16，64…(4的某次方)
 * 谁最先把草吃完，谁获胜
 * 假设牛和羊都绝顶聪明，都想赢，都会做出理性的决定
 * 根据唯一的参数N，返回谁会赢
 * date:    2023/8/16 23:33
 * version:    1.0
 */
public class EatGrass {

    public static String compare(int rest) {
        HashMap<Integer, String> dp = new HashMap<>();
        dp.put(1, "Early");
        return process(rest, dp);
    }

    public static String process(int rest, HashMap<Integer, String> dp) {
        if (dp.containsKey(rest)) {
            return dp.get(rest);
        }
        int next = rest - 1;
        int i = 1;
        while (next >= 0) {
            if (next == 0 || process(next, dp).equals("Late")) {
                dp.put(rest, "Early");
                return "Early";
            }
            next = rest - (1 << (i++ * 2));
        }
        dp.put(rest, "Late");
        return "Late";
    }

    public static String awesome(int rest){
        return (rest % 5 == 0 || rest % 5 == 2) ? "Late" : "Early";
    }

    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            String r1 = compare(i);
            String r2 = awesome(i);
            if (!r1.equals(r2)){
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
package my_practise.p2023.basic.algorithm.bitoperation;

import tool.Tools;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.bitoperation
 * className:      Arithmetic
 * author:     BangDi
 * description:  位运算实现四则运算
 * date:    2023/5/19 16:27
 * version:    1.0
 */
public class Arithmetic {

    public static int add(int a, int b) {
        int eor = a ^ b;
        int forward = (a & b) << 1;
        if (forward != 0) {
            return add(eor, forward);
        }
        return eor;
    }

    public static int minus(int a, int b) {
        return add(a, ~b + 1);
    }

    public static int multi(int a, int b) {
        int result = 0;
        int i = 0;
        while (b != 0) {
            if ((b & 1) == 1) {
                result += a << i;
            }
            b >>= 1;
            i++;
        }
        return result;
    }

    public static int sub(int a, int b) {
        if (a < b) {
            return 0;
        }
        return sub(minus(a,b),b) + 1;
    }

    public static void test() {
        int testTime = 1000000;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int a = Tools.getRandomInt(maxValue, false);
            int b = Tools.getRandomInt(maxValue, false) + 1;
//            System.out.println("a:" + a);
//            System.out.println("b:" + b);
            if ((a + b) != add(a, b) | (a - b) != minus(a, b) | (a * b) != multi(a, b) | (a / b) != sub(a, b)) {
                succeed = false;

            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
    }
}

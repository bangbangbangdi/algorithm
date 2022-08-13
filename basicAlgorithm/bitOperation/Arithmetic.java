package basicAlgorithm.bitOperation;

import tool.Tools;

// 用位运算实现四则运算
public class Arithmetic {
    public static int add(int a, int b) {
//        System.out.println("add");
        int eor = a ^ b;
        //int forward = a & b << 1; 位运算优先级高于逻辑运算，所以这么写有问题
        int forward = (a & b) << 1;
        if (forward == 0) {
            return eor;
        }
        return add(eor, forward);
    }

    public static int minus(int a, int b) {
        b = ~b + 1;
        return add(a, b);
    }

    public static int multi(int a, int b) {
//        System.out.println("multi");
        // 乘法的思路在于
        // 01010 * 0011 = (01010 << 1) + (01010 << 0)
        int i = 0;
        int res = 0;
        // 此方法要求除数不能是负数 因为符号位不参与位运算 所以-1 永远不可能变为0
        while (b != 0) {
            if ((b & 1) != 0) {
                res += a << i;
            }
            b >>= 1;
            i++;
        }
        return res;
    }

    public static int sub(int a, int b) {
//        System.out.println("sub");
        // 除法的思路在于 a作为靶子够b打多少枪
        int ans = 0;
        if (a < b) {
            return 0;
        }
        return sub(minus(a, b), b) + 1;
    }

    public static void test(){
        int testTime = 1000000;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int a = Tools.getRandomInt(maxValue,false);
            int b = Tools.getRandomInt(maxValue,false)+1;
//            System.out.println("a:" + a);
//            System.out.println("b:" + b);
            if ((a+b) != add(a,b)|(a-b) !=minus(a,b) | (a*b) != multi(a,b) | (a/b) != sub(a,b)){
                succeed = false;

            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
    }
}

package my_practise.p2023.basic.algorithm.bitoperation;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.bitoperation
 * className:      Swap
 * author:     BangDi
 * description:  不用中间变量交换两个数
 * date:    2023/5/19 9:55
 * version:    1.0
 */
public class Swap {

    public static void swap(int num1,int num2){
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        num1 = num1 ^ num2;
        num2 = num1 ^ num2;
        num1 = num1 ^ num2;
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
    }

    public static void main(String[] args) {
        swap(10,20);
    }

}

package basicAlgorithm.dynamicProgramming.leftToRight;

// 规定1和A对应,2和B对应,3和C对应...
// 那么一个数字字符串111就可以转化为
// AAA  KA AK
// 给定一个只有数字字符组成的字符串str,返回有多少种转化结果
public class ConvertToLetterString {

    public static int number(String str){
        if (str == null || str.length() == 0){
            return 0;
        }

        return 1;
    }

    public static int process(char[] chars,int index){
        if (index == chars.length){
            return 1;
        }
        return 2;
    }

}

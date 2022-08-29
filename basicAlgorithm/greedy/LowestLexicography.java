package basicAlgorithm.greedy;
//给定一个由字符串组成的数组strs，
//必须把所有的字符串拼接起来，
//返回所有可能的拼接结果中，字典序最小的结果

import java.util.HashSet;

// 这道题首先要将字符串的字典序抽象为数字的排列顺序 也就是将 "abc" --> a*26^2 + b*26^1 + c*26^0
// 此时我们就可以将字符串问题转化为数字问题
// 求字符串字典序最小就等同于求转化后数字最小
// 但是比较鸡贼的地方在于  ba --> 21  b -->2
// 对于上述两个数字要进行字典序的比较的话
public class LowestLexicography {

    public static String lowestLexicography(String[] arr){
        return "a";
    }

    public static String compare(String[] arr){
        if (arr == null || arr.length < 1){
            return "";
        }


        return "1";
    }

    public static String process(String[] arr, int index, StringBuilder path, HashSet<String> set){
        if (index == arr.length){
            return path.toString();
        }
        return "1";
    }

}

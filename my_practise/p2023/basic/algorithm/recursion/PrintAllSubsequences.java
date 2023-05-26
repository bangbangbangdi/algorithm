package my_practise.p2023.basic.algorithm.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.recursion
 * className:      PrintAllSubsequences
 * author:     BangDi
 * description:  打印所有子序列
 * date:    2023/5/26 13:35
 * version:    1.0
 */
public class PrintAllSubsequences {

    public static void printALlSubsequences(String str) {
        if (str == null || str.length() < 1) {
            return;
        }
        ArrayList<String> arr = new ArrayList<>();
        process(str.toCharArray(),0,"",arr);
        arr.forEach(System.out::println);
    }

    public static void process(char[] chars, int index, String path, ArrayList<String> arr) {
        if (index >= chars.length) {
            arr.add(path);
            return;
        }
        process(chars, index + 1, path, arr);
        process(chars, index + 1, path + chars[index], arr);
    }

    public static void main(String[] args) {
        printALlSubsequences("abc");
    }

}

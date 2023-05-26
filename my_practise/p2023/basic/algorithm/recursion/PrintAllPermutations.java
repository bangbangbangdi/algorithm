package my_practise.p2023.basic.algorithm.recursion;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.recursion
 * className:      PrintAllPermutations
 * author:     BangDi
 * description:  打印全排列 (26字母内)
 * date:    2023/5/26 11:10
 * version:    1.0
 */
public class PrintAllPermutations {

    public static void printAllPermutations(String str) {
        if (str == null || str.length() < 1) {
            return;
        }
        process(str.toCharArray(), 0);
    }

    public static void process(char[] chars, int index) {
        if (index == chars.length - 1) {
            System.out.println(String.valueOf(chars));
        }
        boolean[] visit = new boolean[26];
        for (int i = index; i < chars.length; i++) {
            if (visit[chars[i] - 97]) {
                continue;
            }
            visit[chars[i] - 97] = true;
            swap(chars, index, i);
            process(chars, index + 1);
            swap(chars, index, i);
        }
    }

    public static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {
        printAllPermutations("abc");
    }

}

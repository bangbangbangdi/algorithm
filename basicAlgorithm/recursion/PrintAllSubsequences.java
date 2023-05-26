package basicAlgorithm.recursion;

import java.util.ArrayList;

public class PrintAllSubsequences {

    public static void printAllSubsequences(String str) {
        if (str == null || str.length() < 2) {
            return;
        }
        ArrayList<String> arr = new ArrayList<>();
        process(arr,str,"",0);
        arr.forEach(System.out::println);
    }

    public static void process(ArrayList<String> arr, String str, String path, int index) {
        if (index == str.length()) {
            if (path.length() != 0) {
                arr.add(path);
            }
            return;
        }
        process(arr, str, path, index + 1);
        process(arr, str, path + str.charAt(index), index + 1);
    }

    public static void main(String[] args) {
        printAllSubsequences("abc");
    }
}

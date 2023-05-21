package my_practise.p2022.basic.myClass09;

import java.util.ArrayList;
import java.util.HashSet;

public class Code02_Light {

    public static int light1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        ArrayList<String> arr = new ArrayList<>();
        process(str, 0, "", arr);
//        for (int i = 0; i < arr.size(); i++) {
//            System.out.println(arr.get(i));
//        }
        int minCount = Integer.MAX_VALUE;
        int oneCount = Integer.MAX_VALUE;
        String path = null;
        char[] chars = null;
        for (int i = 0; i < arr.size(); i++) {
            path = arr.get(i);
            chars = path.toCharArray();
            oneCount = 0;
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == 'v') {
                    oneCount++;
                }
            }
            minCount = Math.min(minCount, oneCount);
        }
        return minCount;
    }

    // x 表示墙
    // . 表示居民区
    // v 表示路灯
    public static void process(String str, int index, String path, ArrayList<String> all) {
        if (index == str.length()) {
            char[] chars = path.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == 'v') {
                    continue;
                }
                if (chars[i] == '.') {
                    if (i == 0 && i + 1 == chars.length) {
                        return;
                    }
                    if (i == 0 && i + 1 < str.length() && chars[i + 1] != 'v') {
                        return;
                    }
                    if (0 < i && i < chars.length - 1 && !(chars[i - 1] == 'v' || chars[i + 1] == 'v')) {
                        return;
                    }
                    if (i == chars.length - 1 && chars[i - 1] != 'v') {
                        return;
                    }
                }
            }
            all.add(path);
            return;
        }
        char[] chars = str.toCharArray();
        if (chars[index] == 'x') {
            process(str, index + 1, path + "x", all);
        }
        if (chars[index] == '.') {
            // 放路灯的情况
            process(str, index + 1, path + "v", all);
            // 不放灯的情况
            process(str, index + 1, path + ".", all);
        }
    }

    public static int light2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process2(str.toCharArray(), 0, new HashSet<Integer>());
    }

    //
    public static int process2(char[] chars, int index, HashSet<Integer> light) {
        if (index == chars.length) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '.' && !light.contains(i - 1) && !light.contains(i) && !light.contains(i + 1)) {
                    return Integer.MAX_VALUE;
                }
            }
            return light.size();
        }

        int no = process2(chars, index + 1, light);
        int yes = Integer.MAX_VALUE;
        if (chars[index] == '.') {
            light.add(index);
            yes = process2(chars, index + 1, light);
            light.remove(index);
        }
        return Math.min(no, yes);
    }

    public static int light3(String str) {
        int light = 0;
        char[] chars = str.toCharArray();
        int index = 0;
        while (index < chars.length) {
            if (chars[index] == '.') {
                light++;
                if (index + 1 == chars.length) {
                    break;
                }
                if (chars[index + 1] == 'x') {
                    index += 2;
                } else {
                    index += 3;
                }
            } else {
                index++;
            }
        }

        return light;
    }

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'x' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int testTimes = 500000;
        int len = 10;
        boolean succeed = true;
        for (int i = 0; i < testTimes && succeed; i++) {
            String s = randomString(len);
            if (light1(s) != light2(s) || light2(s) != light3(s)) {
                System.out.println(s);
                System.out.println("light1 " + light1(s));
                System.out.println("light2 " + light2(s));
                System.out.println("light3 " + light3(s));
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }
}

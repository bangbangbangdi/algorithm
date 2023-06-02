package my_practise.p2023.basic.algorithm.greedy;

import java.util.ArrayList;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.greedy
 * className:      Light
 * author:     BangDi
 * description:  放灯
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 * date:    2023/6/2 11:50
 * version:    1.0
 */
public class Light {

    public static int light(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int index = 0;
        int res = 0;
        while (index < chars.length) {
            if (chars[index] == 'X') {
                index++;
            } else {
                res++;
                if (index + 1 < chars.length) {
                    if (chars[index + 1] == 'X') {
                        index += 2;
                    } else {
                        index += 3;
                    }
                }else {
                    index++;
                }
            }
        }
        return res;
    }

    public static int compare(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chs = str.toCharArray();
        StringBuilder path = new StringBuilder();
        ArrayList<String> arr = new ArrayList<>();
        process(chs, 0, path, arr);
//        arr.forEach(System.out::println);
        int min = Integer.MAX_VALUE;
        for (String s : arr) {
            char[] chars = s.toCharArray();
            boolean valid = true;
            // 判断arr内的str是否有效
            for (int i = 0; i < chars.length && valid; i++) {
                if (chars[i] == '.') {
                    if (i == 0) {
                        valid = chars.length != 1 && chars[1] == 'V';
                    } else if (i == chars.length - 1) {
                        valid = chars[i - 1] == 'V';
                    } else {
                        valid = chars[i - 1] == 'V' || chars[i + 1] == 'V';
                    }
                }
            }
            min = valid ? Math.min(min, lightCount(chars)) : min;
        }
        return min;
    }

    public static int lightCount(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        int res = 0;
        for (char c : chars) {
            res += c == 'V' ? 1 : 0;
        }
        return res;
    }

    public static void process(char[] chs, int index, StringBuilder path, ArrayList<String> arr) {
        if (index >= chs.length) {
            arr.add(path.toString());
            return;
        }
        if (chs[index] == 'X') {
            process(chs, index + 1, path.append(chs[index]), arr);
            path.delete(path.length() - 1, path.length());
        } else {
            process(chs, index + 1, path.append("V"), arr);
            path.delete(path.length() - 1, path.length());
            process(chs, index + 1, path.append("."), arr);
            path.delete(path.length() - 1, path.length());
        }
    }

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            String test = randomString(len);
            int ans1 = light(test);
            int ans2 = compare(test);
            if (ans1 != ans2) {
                System.out.println(test);
                System.out.println(ans1);
                System.out.println(ans2);
                succeed = false;
            }
        }
        System.out.println(succeed ? "Nice" : "F");
    }
}

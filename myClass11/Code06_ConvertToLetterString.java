package myClass11;

public class Code06_ConvertToLetterString {
    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(0, str.toCharArray());
    }

    public static int process(int index, char[] chars) {
        if (index == chars.length) {
            return 1;
        }
        if (index > chars.length || chars[index] == '0') {
            return 0;
        }

        if (chars[index] == '1') {
            int i = process(index + 1, chars);
            int j = process(index + 2, chars);
            return i + j;
        }

        if (chars[index] == '2') {
            int i = process(index + 1, chars);
            int j = 0;
            if (chars[index + 1] - 48 < 7) {
                j = process(index + 2, chars);
            }
            return i + j;
        }
        return process(index + 1, chars);
    }

    public static void main(String[] args) {
        String str = "11111";
        System.out.println(number(str));
    }
}
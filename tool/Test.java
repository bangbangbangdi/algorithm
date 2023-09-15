package tool;


public class Test {

    public static int manacher(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = getChars(str);
        int[] pArr = new int[chars.length];
        int R = -1;
        int C = -1;
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;
            while(i + pArr[i] < chars.length && i - pArr[i] > -1){
                if (chars[i + pArr[i]] == chars[i - pArr[i]]){
                    pArr[i]++;
                }else {
                    break;
                }
            }
            if (R < i + pArr[i]) {
                C = i;
                R = i + pArr[i];
            }
            max = Math.max(max, pArr[i]);
        }

        return max - 1;
    }

    public static char[] getChars(String str) {
        char[] chars = new char[str.length() * 2 + 1];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (i & 1) == 0 ? '#' : str.charAt(i / 2);
        }
        return chars;
    }

    public static int compare(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chs = getChs(str);
        int offset = 1;
        int max = 0;
        int cur = 1;
        for (int i = 0; i < chs.length; i++) {
            while (i - offset > -1 && i + offset < chs.length) {
                if (chs[i - offset] == chs[i + offset]) {
                    offset++;
                    cur += 2;
                } else {
                    break;
                }
            }
            max = Math.max(max, cur);
            offset = 1;
            cur = 1;
        }
        return max / 2;
    }


    public static char[] getChs(String str) {
        char[] chars = new char[str.length() * 2 + 1];
        char[] arr = str.toCharArray();
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (i & 1) == 0 ? '#' : arr[index++];
        }
        return chars;
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 10;
        int maxValue = 3;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            String str = Tools.generateRandomString(maxSize, maxValue);
            int r1 = manacher(str);
            int r2 = compare(str);
            System.out.println("str = " + str);
            System.out.println("r1 = " + r1);
            if (r1 != r2) {
                succeed = false;
                System.out.println("str = " + str);
                System.out.println("r1 = " + r1);
                System.out.println("r2 = " + r2);
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        test();
    }

}

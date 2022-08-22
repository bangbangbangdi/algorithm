package basicAlgorithm.dynamicProgramming.leftToRight;

public class StickersToSpellWord {

    public static int stickers(String str, String[] arr) {
        if (str == null || arr == null || str.length() < 1 || arr.length < 1) {
            return -1;
        }
        char[] chars = str.toCharArray();
//        return process(chars,0,1);
        return 1;
    }

    public static int process(char[] chars, int count, int index, String[] arr) {
        return 1;
    }

}

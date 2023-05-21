package my_practise.p2023.basic.algorithm.bitoperation;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.bitoperation
 * className:      EvenTimesOddsTimes
 * author:     BangDi
 * description:
 * // 数组中有一个数出现了奇数次，其余都出现了偶数次，请找出这种数
 * // 数组中有两个数出现了奇数次，其余都出现了偶数次，请找出这种数
 * <p>
 * date:    2023/5/19 10:05
 * version:    1.0
 */
public class EvenTimesOddTimes {

    public static int oneOddTimes(int[] arr) {
        if (arr == null || arr.length == 0) {
            return Integer.MIN_VALUE;
        }
        int reduce = Arrays.stream(arr).reduce(0, (a, b) -> a ^ b);
        return reduce;
    }

    public static int oneOddTimesMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            return Integer.MIN_VALUE;
        }
        int result = 0;
        for (int j : arr) {
            result ^= j;
        }
        return result;
    }

    public static int[] twoOddTimes(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] result = new int[2];
        IntStream stream = Arrays.stream(arr);
        int eor = stream.reduce(0, (a, b) -> a ^ b);
        int rightOne = eor & (~eor + 1);
        for (int num : arr) {
            result[0] = (num & rightOne) == 0 ? result[0] : result[0] ^ num;
        }
        result[1] = result[0] ^ eor;
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3, 4, 4};
        int oddNum = oneOddTimes(arr);
        int oddNum2 = oneOddTimesMin(arr);
        System.out.println(oddNum);
        System.out.println(oddNum2);
        System.out.println("-----------");
        int[] arr2 = {1,1,5,3,4,4};
        int[] result = twoOddTimes(arr2);
        System.out.println("result[0] = " + result[0]);
        System.out.println("result[1] = " + result[1]);
    }

}

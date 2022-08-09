package myPractice.bitOperation;

import tool.Tools;

import java.util.HashMap;
import java.util.Optional;

// 数组中有一个数出现了奇数次，其余都出现了偶数次，请找出这种数
// 数组中有两个数出现了奇数次，其余都出现了偶数次，请找出这种数
public class EvenTimesOddTimes {

    public static int getOddTimesNum1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int ans = arr[0];
        for (int i = 1; i < arr.length; i++) {
            ans ^= arr[i];
        }
        return ans;
    }

    public static int normalWay(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        // 建立一个简单的词频统计
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        map.forEach((k,v) -> System.out.println(k+" : "+v));



        // 筛选出词频为奇数的元素
        Optional<Integer> first = map.keySet().stream().filter(key -> map.get(key) % 2 == 1).findFirst();


        return first.orElse(-1);
    }

    public static void test() {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime && succeed; i++) {
            int[] arr = Tools.generateRandomArray(maxSize, maxValue, false);
            int odd1 = getOddTimesNum1(arr);
            int odd2 = normalWay(arr);
            if (odd1 != odd2){
                succeed = false;
                Tools.printArray(arr);
                System.out.println(odd1);
                System.out.println(odd2);
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }

    public static void main(String[] args) {
        int[] arr = {1,1,2,2,3,3,4};
        System.out.println(getOddTimesNum1(arr));
        System.out.println(normalWay(arr));
    }
}

package basicAlgorithm.bitOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

// 数组中有一个数出现了奇数次，其余都出现了偶数次，请找出这种数
// 数组中有两个数出现了奇数次，其余都出现了偶数次，请找出这种数
public class EvenTimesOddTimes {

    // 数组中有一个数出现了奇数次，其余都出现了偶数次，请找出这种数
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

    // 数组中有两个数出现了奇数次，其余都出现了偶数次，请找出这种数
    public static int getOddTimesNum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int eor = arr[0];
        for (int i = 1; i < arr.length; i++) {
            // 这一步做完后得到的是a^b
            eor ^= arr[i];
        }
        // 这一步是把二进制最右边的一挑出来
        int rightOne = eor & (~eor + 1);

        //现在就可以根据这个最右边的1，将数组内的数字划分为两类
        //1.该位上是1的数字
        //2.该位上是0的数字

        int ans1 = arr[0];
        // 这个for循环跑完之后ans1里就会存a或b的其中一个
        for (int i = 1; i < arr.length; i++) {
            if ((eor & arr[i]) == 0){
                ans1 ^= arr[i];
            }
        }

        int ans2 = eor ^ ans1;

        System.out.println("ans1:" + ans1 +" "+"ans2 :"+ans2 );

        return eor;
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
//        map.forEach((k, v) -> System.out.println(k + " : " + v));

        ArrayList<Integer> list = new ArrayList<>();
        // 筛选出词频为奇数的元素
        List<Integer> collect = map.keySet().stream().filter(key -> map.get(key) % 2 == 1).collect(Collectors.toList());
        System.out.println(collect.get(0));
        System.out.println(collect.get(1));

        return -1;
    }



    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 3, 4,5};
        getOddTimesNum2(arr);
        normalWay(arr);
    }
}

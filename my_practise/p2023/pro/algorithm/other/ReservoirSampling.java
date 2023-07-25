package my_practise.p2023.pro.algorithm.other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm.other
 * className:      ReservoirSampling
 * author:     BangDi
 * description:蓄水池算法
 * 假设有个机器源源不断的在吐出从零开始编号加一的球
 * 只提供一个能装十个球的袋子,在每一次吐出球的时候只能决定它是进入袋子还是丢掉
 * 丢掉的球永远都找不回来了
 * 要求:等概率的将球放进袋子中 例如:吐出100个球,则每个球进入袋子的概率都是10/100
 * date:    2023/7/20 23:57
 * version:    1.0
 */
public class ReservoirSampling {

    public static class RandomBox {
        // 记录添加吐出的总球数
        private int N;
        // 袋子里的球
        private int[] bag;
        // 袋子容量
        private int capacity;

        public RandomBox(int capacity) {
            this.N = 0;
            this.bag = new int[capacity];
            this.capacity = capacity;
        }

        private int random(int N) {
            return (int) (1 + N * Math.random());
        }

        public void add(int num) {
            N++;
            if (N <= capacity) {
                bag[N - 1] = num;
            } else {
                if (random(N) <= capacity) {
                    bag[random(capacity) - 1] = num;
                }
            }
        }

        public int[] choices() {
            int[] ints = new int[capacity];
            System.arraycopy(bag, 0, ints, 0, bag.length);
            return ints;
        }
    }


    public static void test() {
        System.out.println("hello");
        int all = 100;
        int choose = 10;
        int testTimes = 50000;
        int[] counts = new int[all + 1];
        for (int i = 0; i < testTimes; i++) {
            RandomBox box = new RandomBox(choose);
            for (int num = 1; num <= all; num++) {
                box.add(num);
            }
            int[] ans = box.choices();
            for (int j = 0; j < ans.length; j++) {
                counts[ans[j]]++;
            }
        }

        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + " times : " + counts[i]);
        }
    }

    public static void main(String[] args) {
        test();
    }

}

package my_practise.p2023.pro.algorithm.other;

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

    /**
     * @param size   袋子的最大容量
     * @param maxInt 从0开始吐到最大球的编号
     * @return 吐出的球放到Set里
     */
    public static int[] reservoirSampling(int size, int maxInt) {
        if (size <= 0 || maxInt <= 0) {
            return null;
        }
        int[] res = new int[size];
        int index = 0;
        int rand = 0;
        while (index <= maxInt) {
            if (index < size) {
                res[index] = index;
            } else {
                rand = (int) ((index + 1) * Math.random());
                if (rand > index){

                }
            }
            index++;
        }
        return res;
    }

}

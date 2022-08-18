package basicAlgorithm.dynamicProgramming.leftToRight;

// 给定两个长度都为N的数组weights和values
// weights[i]和values[i]分别表示i号物品的重量和价值
// 给你一个正数bag表示载重为bag的袋子
// 要装的商品不能超过该重量,请返回能装下的最大价值
public class Knapsack {

    public static int getMaxValue(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length != values.length || weights.length < 1 || bag < 0) {
            return 0;
        }
        return 1;
    }

    public static int process(int[] weights, int[] values, int bag ,int get,int index) {
        return 0;
    }

}

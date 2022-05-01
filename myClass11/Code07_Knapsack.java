package myClass11;

public class Code07_Knapsack {

    public static int getMaxValue(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length != values.length) {
            return 0;
        }
        return process(weights, values, 0, bag);
    }

    public static int process(int[] weights, int[] values, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index >= weights.length) {
            return 0;
        }
        int no = process(weights, values, index + 1, rest);
        int yes = Integer.MIN_VALUE;
        if (rest >= weights[index]) {
            yes = values[index] + process(weights, values, index + 1, rest - weights[index]);
        }
        return Math.max(yes, no);
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(getMaxValue(weights, values, bag));
    }

}
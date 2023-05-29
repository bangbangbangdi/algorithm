package my_practise.p2023.basic.algorithm.dynamicProgramming.scope;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.algorithm.dynamicProgramming.scope
 * className:      CardsInLine
 * author:     BangDi
 * description:  扑克牌
 * // 给定一个整形数组arr,代表数值不同的纸牌排成一条线
 * // 玩家A和玩家B依次拿走每张牌
 * // 规定玩家A先拿,玩家B后拿
 * // 但是每个玩家每次只能拿走最走或最右的纸牌
 * // 玩家A和玩家B都绝顶聪明,请返回最后获胜者的分数
 * date:    2023/5/29 18:47
 * version:    1.0
 */
public class CardsInLine {

    public static int cardsInLine(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    public static int f(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        return Math.max(arr[L] + s(arr, L + 1, R), arr[R] + s(arr, L, R - 1));
    }

    public static int s(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        return Math.min(f(arr, L + 1, R), f(arr, L, R - 1));
    }

    public static void main(String[] args) {
        int[] arr = {5,10,20,7};
        System.out.println(cardsInLine(arr));
    }
}

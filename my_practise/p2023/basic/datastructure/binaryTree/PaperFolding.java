package my_practise.p2023.basic.datastructure.binaryTree;

/**
 * projectName:    algorithm1
 * package:        my_practise.p2023.basic.datastructure.binaryTree
 * className:      PaperFolding
 * author:     BangDi
 * description:  折纸
 * // 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
 * 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * // 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。 请从上到下打印所有折痕的方向。
 * // 例如:N=1时，打印: down N=2时，打印: down down up
 * date:    2023/6/5 11:47
 * version:    1.0
 */
public class PaperFolding {

    // 这道题除了考察常规的coding能力外还重点考察了观察和提取共同规律的能力
    public static void paperFolding(int N){
        if (N <= 0){
            return ;
        }
        process(N,"down");
    }

    public static void process(int N,String str){
        if (N == 0){
            return ;
        }
        process(N-1,"down");
        System.out.println(str);
        process(N-1,"up");
    }

    public static void main(String[] args) {
        paperFolding(3);
    }

}

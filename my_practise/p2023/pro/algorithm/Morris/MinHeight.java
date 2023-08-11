package my_practise.p2023.pro.algorithm.Morris;

import tool.BtNode;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.pro.algorithm.Morris
 * className:      MinHeight
 * author:     BangDi
 * description:  TODO
 * date:    2023/8/7 20:04
 * version:    1.0
 */
public class MinHeight {

    public static int morrisMinHeight(BtNode head) {
        BtNode cur = head;
        BtNode mostLeftRight = null;
        int min = Integer.MAX_VALUE;
        int curH = 0;
        int curMinus = 0;
        while (cur != null) {
            if (cur.left != null) {
                mostLeftRight = cur.left;
                while (mostLeftRight.right != null && mostLeftRight.right != cur) {
                    curMinus++;
                    mostLeftRight = mostLeftRight.right;
                }
                if (mostLeftRight.right == null) {
                    mostLeftRight.right = cur;
                    cur = cur.left;
                    curH++;
                    continue;
                } else {
                    mostLeftRight.right = null;
                    if (mostLeftRight.left == null) {
                        curH--;
//                        System.out.println("cur.value = " + cur.value);
//                        System.out.println("curH = " + curH);
//                        System.out.println("curMinus = " + curMinus);
//                        System.out.println("================");
                        min = Math.min(curH, min);
                        curH -= curMinus;
//                        System.out.println("curH = " + curH);
                    }
                }
            }
            curH++;
            cur = cur.right;
        }
        BtNode mostRight = head;
        while (mostRight.right != null) {
            mostRight = mostRight.right;
        }
        if (mostRight.left == null) {
            min = Math.min(--curH, min);
        }
        return min;
    }

    public static void main(String[] args) {
        BtNode n1 = new BtNode(1);
        BtNode n2 = new BtNode(2);
        BtNode n3 = new BtNode(3);
        BtNode n4 = new BtNode(4);
        BtNode n5 = new BtNode(5);
//        BtNode n6 = new BtNode(6);
//        BtNode n7 = new BtNode(7);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
//        n3.left = n6;
//        n3.right = n7;

        System.out.println(morrisMinHeight(n1));
    }

}

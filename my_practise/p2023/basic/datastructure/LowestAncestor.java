package my_practise.p2023.basic.datastructure;

import tool.BtNode;

/**
 * projectName:    algorithm
 * package:        my_practise.p2023.basic.datastructure
 * className:      LowestAncestor
 * author:     BangDi
 * description:  TODO
 * 给定一颗二叉树的头节点和a,b两个节点
 * 返回a,b最低的公共祖先
 * date:    2023/6/3 22:20
 * version:    1.0
 */
public class LowestAncestor {

    public static BtNode lowestAncestor(BtNode head, BtNode a, BtNode b) {
        if (head == null || a == null || b == null || a == b) {
            return a;
        }
        return process(head, a, b).ancestor;
    }

    public static Info process(BtNode cur, BtNode a, BtNode b) {
        if (cur == null) {
            return new Info(false, false, null);
        }
        Info left = process(cur.left, a, b);
        Info right = process(cur.right, a, b);
        if (left.ancestor != null || right.ancestor != null) {
            return left.ancestor != null ? left : right;
        }
        boolean findA = left.findA || right.findA || cur == a;
        boolean findB = left.findB || right.findB || cur == b;
        BtNode ancestor = findA && findB ? cur : null;
        return new Info(findA, findB, ancestor);
    }

    public static class Info {
        boolean findA;
        boolean findB;
        BtNode ancestor;

        public Info(boolean findA, boolean findB, BtNode ancestor) {
            this.findA = findA;
            this.findB = findB;
            this.ancestor = ancestor;
        }
    }

    public static void test() {
        BtNode n0 = new BtNode(5);
        BtNode n1 = new BtNode(3);
        BtNode n2 = new BtNode(7);
        BtNode n3 = new BtNode(2);
        BtNode n4 = new BtNode(4);
        BtNode n5 = new BtNode(6);
        BtNode n6 = new BtNode(8);
        BtNode n7 = new BtNode(8);
        BtNode n8 = new BtNode(9);
        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;
        n3.left = n7;
//        n7.left = n8;
        System.out.println(lowestAncestor(n0, n1, n3).value);
    }

    public static void main(String[] args) {
        test();
    }

}
